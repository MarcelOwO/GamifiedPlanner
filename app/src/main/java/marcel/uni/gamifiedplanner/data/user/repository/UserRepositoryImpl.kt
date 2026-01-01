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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.data.user.dto.TaskLogDto
import marcel.uni.gamifiedplanner.data.user.dto.UserDto
import marcel.uni.gamifiedplanner.data.user.dto.UserInventoryItemDto
import marcel.uni.gamifiedplanner.data.user.dto.mapper.toDomain
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.domain.user.model.UserSettings
import marcel.uni.gamifiedplanner.domain.user.model.UserInventory
import marcel.uni.gamifiedplanner.domain.user.model.UserProfile
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievementItem
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult
import marcel.uni.gamifiedplanner.util.observeList
import marcel.uni.gamifiedplanner.util.observeModel
import marcel.uni.gamifiedplanner.util.firebaseConstants

class UserRepositoryImpl(
    private val db: FirebaseFirestore,
) : UserRepository {
    private fun userRef(uid: String) = db.collection(firebaseConstants.USERS).document(uid)

    private fun inventoryColl(uid: String) = userRef(uid).collection(firebaseConstants.INVENTORY)

    override fun observeUser(uid: String): Flow<User?> = userRef(uid).observeModel<UserDto>().map { it?.toDomain() }


    override suspend fun addXp(
        uid: String,
        amount: Long,
    ) {
        userRef(uid).update("xp", FieldValue.increment(amount)).await()
    }

    override suspend fun purchaseItem(
        uid: String,
        itemId: String,
        cost: Long,
    ) = runCatching {
        db
            .runTransaction { tx ->
                val balance = tx.get(userRef(uid)).getLong("currency") ?: 0L
                if (balance < cost) throw Exception("Not enough money")
                tx.update(userRef(uid), "currency", balance - cost)
                tx.set(inventoryColl(uid).document(itemId), mapOf("id" to itemId))
            }.await()
    }



    override  fun observeSettings(userId: String): Flow<UserSettings> = userRef(userId).observeModel<UserSettings>()

    override suspend fun updateSettings(userId: String, new: UserSettings) {
        userRef(userId).set(new,SetOptions.merge()).await()
    }

    override fun observeProfile(userId: String): Flow<UserProfile> = userRef(userId).observeModel<UserProfile>()

    override suspend fun updateProfile(userId: String, new: UserProfile) {
        userRef(userId).set(new,SetOptions.merge()).await()
    }

    override fun observeInventory(userId: String): Flow<UserInventory> = userRef(userId).observeModel<UserInventory>()

    override suspend fun updateInventory(userId: String, new: UserInventory) {
        userRef(userId).set(new,SetOptions.merge()).await()
    }

    override fun observeStats(userId: String): Flow<UserStats> = userRef(userId).observeModel<UserStats>()

    override suspend fun updateStats(userId: String, new: UserStats) {
        userRef(userId).set(new,SetOptions.merge()).await()

    }
