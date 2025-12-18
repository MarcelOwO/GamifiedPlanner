package marcel.uni.gamifiedplanner.data.user.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.data.user.dto.TaskLogDto
import marcel.uni.gamifiedplanner.data.user.dto.ToDomain
import marcel.uni.gamifiedplanner.data.user.dto.UserAchievementDto
import marcel.uni.gamifiedplanner.data.user.dto.UserDto
import marcel.uni.gamifiedplanner.data.user.dto.UserInventoryItemDto
import marcel.uni.gamifiedplanner.data.user.dto.toDomain
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievement
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class UserRepositoryImpl(
    private val auth: FirebaseAuthRepository,
    private val firestore: FirebaseFirestore,
) : UserRepository {

    private val uid: String?
        get() = auth.currentUserId


    private fun getUserDoc(uid: String) = firestore.collection("users").document(uid)
    private fun getAchievementsColl(uid: String) = getUserDoc(uid).collection("achievements")
    private fun getTaskHistoryColl(uid: String) = getUserDoc(uid).collection("task_history")
    private fun getShopItemsColl() = firestore.collection("shop_items")
    private fun getInventoryColl(uid: String) = getUserDoc(uid).collection("inventory")

    override suspend fun createUserProfile(username: String) {
        if (uid == null) {
            return;
        }

        val initialData = UserDto(
            uid = uid!!,
            username = username,
            xp = 0,
            currency = 0
        )

        getUserDoc(uid!!).set(initialData).await()
    }


    override fun observeUserStats(): Flow<UserStats?> = callbackFlow {
        if (uid == null) {
            trySend(null)
            return@callbackFlow
        }

        val listener = getUserDoc(uid!!).addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error);
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val userDtos = snapshot.toObject<UserDto>()
                if (userDtos == null) {
                    return@addSnapshotListener
                }
                val user = userDtos.ToDomain()
                trySend(user)
            }
        }
        awaitClose() {
            listener.remove()
        }
    }

    override suspend fun addXp(amount: Int) {
        if (uid == null) {
            return
        }

        getUserDoc(uid!!).update("xp", FieldValue.increment(amount.toLong())).await()
    }

    override suspend fun spendCurrency(amount: Int): Result<Unit> {

        if (uid == null) {
            return Result.failure(IllegalStateException("User not logged in"))
        }

        return try {
            firestore.runTransaction { transaction ->

                val userRef = getUserDoc(uid!!)
                val snapShot = transaction.get(userRef)

                val currentBalance = snapShot.getLong("currency") ?: 0L

                if (currentBalance < amount) {
                    throw IllegalArgumentException("Insufficient funds")
                }

                transaction.update(userRef, "currency", currentBalance - amount)
            }.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun observeAchievementsProgress(): Flow<List<UserAchievement>> = callbackFlow {
        if (uid == null) {
            trySend(emptyList())
            return@callbackFlow
        }

        val listener = getAchievementsColl(uid!!).addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val userAchievementsDto = snapshot.toObjects<UserAchievementDto>()
                val userAchievements = userAchievementsDto.map { it.toDomain() }
                trySend(userAchievements)
            }
        }
        awaitClose() {
            listener.remove()
        }
    }

    override suspend fun unlockAchievement(achivementId: String) {


        if (uid == null) {
            return
        }

        val updateData = UserAchievementDto(
            uuid = uid!!,
            achievementId = achivementId,
            unlockedAt = System.currentTimeMillis()
        )

        getAchievementsColl(uid!!).document(achivementId).set(
            updateData, SetOptions.merge()
        ).await()
    }

    override fun getTaskHistory(
        startTime: Long,
        endTime: Long
    ): Flow<List<TaskHistoryItem>> = callbackFlow {

        if (uid == null) {
            trySend(emptyList())
            return@callbackFlow
        }

        val query = getTaskHistoryColl(uid!!)
            .whereGreaterThanOrEqualTo("completedAt", startTime)
            .whereLessThanOrEqualTo("completedAt", endTime)
            .orderBy("completedAt", Query.Direction.DESCENDING)

        val listener = query.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val completed = snapshot.toObjects<TaskLogDto>()
                val tasks = completed.map { it -> it.toDomain() }
                trySend(tasks)
            }
        }
        awaitClose() {
            listener.remove()
        }
    }

    override suspend fun logCompletedTask(task: TaskHistoryItem) {

        if (uid == null) {
            return
        }

        firestore.runTransaction { transaction ->
            val userRef = getUserDoc(uid!!)
            val newLogRef = getTaskHistoryColl(uid!!).document()

            val xpEarned: Long = when (task.taskPriority) {
                Priority.LOW -> 10L
                Priority.MEDIUM -> 20L
                Priority.HIGH -> 30L
            }
            val logData = mapOf(
                "taskId" to task.taskId,
                "completedAt" to task.completedAt,
                "xpEarned" to xpEarned,
                "priority" to task.taskPriority
            )

            transaction.set(newLogRef, logData)
            transaction.update(userRef, "xp", FieldValue.increment(xpEarned))

        }.await()
    }

    override fun observeInventory(): Flow<List<UserInventoryItem>> = callbackFlow {
        if (uid == null) {
            trySend(emptyList())
            return@callbackFlow
        }

        val listener = getInventoryColl(uid!!).addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val dtos = snapshot.toObjects<UserInventoryItemDto>()
                val userInventoryItems = dtos.map { it.toDomain() }
                trySend(userInventoryItems)
            }
        }
        awaitClose() {
            listener.remove()
        }
    }

    override suspend fun purchaseItem(itemId: String) {
        if (uid == null) {
            return
        }

        firestore.runTransaction { transaction ->
            val userRef = getUserDoc(uid!!)
            val showItemRef = getShopItemsColl().document(itemId)
            val userInventoryRef = getInventoryColl(uid!!).document(itemId)

            val inventorySnapshot = transaction.get(userInventoryRef)
            if (inventorySnapshot.exists())
                throw IllegalStateException("Items already owned")
            val shopItemSnapshot = transaction.get(showItemRef)
            if (!shopItemSnapshot.exists())
                throw IllegalStateException("Item does not exist")

            val cost = shopItemSnapshot.getLong("cost") ?: 0L

            val userSnapshot = transaction.get(userRef)
            val currentBalance = userSnapshot.getLong("currency") ?: 0L

            if (currentBalance < cost)
                throw IllegalArgumentException("Insufficient funds")

            transaction.update(userRef, "currency", currentBalance - cost)

            val newItemData = mapOf(
                "purchasedAt" to System.currentTimeMillis(),
                "itemId" to itemId
            )
            transaction.set(userInventoryRef, newItemData)

        }.await()
    }

    override fun observeDarkMode(): Flow<Boolean> = callbackFlow {
        if (uid == null) {
            trySend(false)
            return@callbackFlow
        }

        val listener = getUserDoc(uid!!).addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val enabled = snapshot.getBoolean("darkMode") ?: false
                trySend(enabled)
            } else trySend(false)

        }

        awaitClose() {
            listener.remove()
        }
    }

    override suspend fun toggleDarkMode(enabled: Boolean) {
        if (uid == null){
            return
        }
        getUserDoc(uid!!).update("darkmode",enabled).await()
    }

    override fun observeNotifications(): Flow<Boolean> = callbackFlow{
        if(uid == null){
            trySend(false)
            return@callbackFlow
        }
        val listener = getUserDoc(uid!!).addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val enabled = snapshot.getBoolean("notifications") ?: false
                trySend(enabled)
            } else trySend(false)

        }

        awaitClose() {
            listener.remove()
        }
    }

    override suspend fun toggleNotifications(enabled: Boolean) {
        if (uid == null)
            return

        getUserDoc(uid!!).update("notifications",enabled).await()
    }

}