package marcel.uni.gamifiedplanner.data.user

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievementItem
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserProfile
import marcel.uni.gamifiedplanner.domain.user.model.UserSettings
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeModel
import marcel.uni.gamifiedplanner.util.observeList

class UserRepositoryImpl(
    private val db: FirebaseFirestore,
) : UserRepository {
    private fun userRef(uid: String) = db.collection(firebaseConstants.USERS)
        .document(uid)

    private fun inventoryColl(uid: String) =
        userRef(uid).collection(firebaseConstants.USERINVENTORY)

    private fun achievementColl(userId: String) =
        userRef(userId).collection(firebaseConstants.USERACHIEVEMENTS)

    private fun historyColl(userId: String) =
        userRef(userId).collection(firebaseConstants.USERTASKHISTORY)

    override fun observeUser(uid: String): Flow<User> =
        userRef(uid).observeModel<User>()

    override fun observeSettings(userId: String): Flow<UserSettings> =
        observeUser(userId).map { it.settings }

    override fun observeStats(userId: String): Flow<UserStats> =
        observeUser(userId).map { it.stats }

    override fun observeProfile(userId: String): Flow<UserProfile> =
        observeUser(userId).map { it.profile }


    override fun observeInventoryItems(userId: String): Flow<List<UserInventoryItem>> =
        inventoryColl(userId).observeList()

    override fun observeAchievementItems(userId: String): Flow<List<UserAchievementItem>> =
        achievementColl(userId).observeList()

    override fun observeHistoryItems(userId: String): Flow<List<TaskHistoryItem>> =
        historyColl(userId).observeList()


    override suspend fun create(uid: String, username: String, email: String) {
        val newUser = User(
            uid = uid,
            profile = UserProfile(
                username = username,
                email = email
            ),
            stats = UserStats(
                xp = 0,
                currency = 0,
            ),
            settings = UserSettings(
                darkMode = true,
                notificationState = false
            )
        )
        userRef(uid).set(newUser).await()

    }

    override suspend fun addXp(
        uid: String,
        amount: Long,
    ) {
        userRef(uid).update(firebaseConstants.FIELD_XP, FieldValue.increment(amount)).await()
    }

    override suspend fun updateSettings(
        uid: String,
        new: UserSettings
    ) {
        userRef(uid).update(firebaseConstants.FIELD_SETTINGS, new).await()
    }

    override suspend fun updateProfile(
        uid: String,
        new: UserProfile
    ) {
        userRef(uid).update(firebaseConstants.FIELD_PROFILE, new).await()
    }

    suspend fun updateStats(
        uid: String,
        new: UserStats
    ) {
        userRef(uid).update(firebaseConstants.FIELD_STATS, new).await()
    }

    override suspend fun purchaseItem(
        uid: String,
        itemId: String,
        cost: Long,
    ): Result<Unit> = runCatching {
        db.runTransaction { tx ->
            val userSnapShot = tx.get(userRef(uid))
            val currentCurrency = userSnapShot.getLong(firebaseConstants.FIELD_CURRENCY) ?: 0L

            if (currentCurrency < cost) {
                throw IllegalStateException("Not enough money")
            }
            tx.update(userRef(uid), firebaseConstants.FIELD_CURRENCY, currentCurrency - cost)

            val newItemRef = inventoryColl(uid).document()

            val newItem = mapOf(
                "id" to newItemRef.id,
                "itemId" to itemId,
                "acquiredAt" to FieldValue.serverTimestamp(),
                "isEquipped" to false,
            )
            tx.set(
                newItemRef, newItem
            )
            tx.set(newItemRef, newItem)
        }.await()
    }

    override suspend fun addTaskHistoryItem(
        uid: String,
        taskHistoryItem: TaskHistoryItem
    ) {
        inventoryColl(uid).add(taskHistoryItem).await()
    }
}