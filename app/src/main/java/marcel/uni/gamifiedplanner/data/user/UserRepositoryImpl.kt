package marcel.uni.gamifiedplanner.data.user

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievementItem
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserProfile
import marcel.uni.gamifiedplanner.domain.user.model.UserSettings
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList
import marcel.uni.gamifiedplanner.util.observeModel
import kotlin.text.set

class UserRepositoryImpl(
    private val db: FirebaseFirestore,
    private val logger: AppLogger,
) : UserRepository {
    private fun userRef(uid: String) =
        db
            .collection(firebaseConstants.USERS)
            .document(uid)

    private fun inventoryColl(uid: String) = userRef(uid).collection(firebaseConstants.USERINVENTORY)

    private fun achievementColl(userId: String) = userRef(userId).collection(firebaseConstants.USERACHIEVEMENTS)

    private fun historyColl(userId: String) = userRef(userId).collection(firebaseConstants.USERTASKHISTORY)

    override fun observeUser(uid: String): Flow<User> =
        userRef(uid).observeModel<User>().catch { e ->
            logger.e("Error observing user: ${e.message}")
            throw e
        }

    override fun observeSettings(userId: String): Flow<UserSettings> =
        observeUser(userId).map { it.settings }.catch { e ->
            logger.e("Error observing user settings: ${e.message}")
            throw e
        }

    override fun observeStats(userId: String): Flow<UserStats> =
        observeUser(userId).map { it.stats }.catch { e ->
            logger.e("Error observing user stats: ${e.message}")
            throw e
        }

    override fun observeProfile(userId: String): Flow<UserProfile> =
        observeUser(userId).map { it.profile }.catch { e ->
            logger.e("Error observing user profile: ${e.message}")
            throw e
        }

    override fun observeInventoryItems(userId: String): Flow<List<UserInventoryItem>> =
        inventoryColl(userId).observeList<UserInventoryItem>().catch { e ->
            logger.e("Error observing user inventory items: ${e.message}")
            throw e
        }

    override fun observeAchievementItems(userId: String): Flow<List<UserAchievementItem>> =
        achievementColl(userId).observeList<UserAchievementItem>().catch { e ->
            logger.e("Error observing user achievement items: ${e.message}")
            throw e
        }

    override fun observeHistoryItems(userId: String): Flow<List<TaskHistoryItem>> =
        historyColl(userId).observeList<TaskHistoryItem>().catch { e ->
            logger.e("Error observing user history items: ${e.message}")
            throw e
        }

    override suspend fun create(
        uid: String,
        username: String,
        email: String,
    ) {
        runCatching {
            val newUser =
                User(
                    uid = uid,
                    profile =
                        UserProfile(
                            username = username,
                            email = email,
                        ),
                    stats =
                        UserStats(
                            xp = 0,
                            currency = 0,
                            streak = 0,
                            lastLogin = Timestamp.now(),
                        ),
                    settings =
                        UserSettings(
                            darkMode = true,
                            notificationState = false,
                        ),
                )
            userRef(uid).set(newUser).await()
        }.onFailure { e ->
            logger.e("Error creating user: ${e.message}")
        }
    }

    override suspend fun updateSettings(
        uid: String,
        new: UserSettings,
    ) {
        userRef(uid).update(firebaseConstants.FIELD_SETTINGS, new).await()
    }

    override suspend fun updateProfile(
        uid: String,
        new: UserProfile,
    ) {
        userRef(uid).update(firebaseConstants.FIELD_PROFILE, new).await()
    }

    override suspend fun updateStats(
        uid: String,
        new: UserStats,
    ) {
        userRef(uid).update(firebaseConstants.FIELD_STATS, new).await()
    }

    override suspend fun purchaseItem(
        uid: String,
        itemId: String,
        cost: Long,
    ): Result<Unit> {
        runCatching {
            db
                .runTransaction { tx ->
                    val userSnapShot = tx.get(userRef(uid))
                    val currentCurrency = userSnapShot.getLong(firebaseConstants.FIELD_CURRENCY) ?: 0L

                    if (currentCurrency < cost) {
                        logger.e("Not enough money to purchase item: $itemId")
                        throw IllegalStateException("Not enough money")
                    }
                    tx.update(userRef(uid), firebaseConstants.FIELD_CURRENCY, currentCurrency - cost)

                    val newItemRef = inventoryColl(uid).document()

                    val newItem =
                        mapOf(
                            "id" to newItemRef.id,
                            "itemId" to itemId,
                            "acquiredAt" to FieldValue.serverTimestamp(),
                            "isEquipped" to false,
                        )
                    tx.set(
                        newItemRef,
                        newItem,
                    )
                    tx.set(newItemRef, newItem)
                }.await()
        }.onFailure { e ->
            logger.e("Error purchasing item: ${e.message}")
            return Result.failure(e)
        }

        return Result.success(Unit)
    }

    override suspend fun addTaskHistoryItem(
        uid: String,
        taskHistoryItem: TaskHistoryItem,
    ) {
        runCatching {
            inventoryColl(uid).add(taskHistoryItem).await()
        }.onFailure { e ->
            logger.e("Error adding task history item: ${e.message}")
        }
    }

    override suspend fun setLastLogin(uid: String) {
        runCatching {
            userRef(uid).update(firebaseConstants.FIELD_LASTLOGIN, FieldValue.serverTimestamp()).await()
        }.onFailure { e ->
            logger.e("Error setting last login: ${e.message}")
        }
    }
}
