package marcel.uni.gamifiedplanner.domain.user.repository

import com.google.firebase.firestore.Transaction
import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievement
import marcel.uni.gamifiedplanner.domain.user.model.UserInventory
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserProfile
import marcel.uni.gamifiedplanner.domain.user.model.UserSettings
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.util.PlannerResult

interface UserRepository {
    fun observeUser(uid: String): Flow<User?>

    suspend fun addXp(
        uid: String,
        amount: Long,
    )

    suspend fun purchaseItem(
        uid: String,
        itemId: String,
        cost: Long,
    ): Result<Transaction?>

    fun observeSettings(userId: String): Flow<UserSettings>

    suspend fun updateSettings(
        userId: String,
        new: UserSettings,
    )

    fun observeProfile(userId: String): Flow<UserProfile>

    suspend fun updateProfile(
        userId: String,
        new: UserProfile,
    )

    fun observeInventory(userId: String): Flow<UserInventory>

    suspend fun updateInventory(
        userId: String,
        new: UserInventory,
    )

    fun observeStats(userId: String): Flow<UserStats>

    suspend fun updateStats(
        userId: String,
        new: UserStats,
    )
}
