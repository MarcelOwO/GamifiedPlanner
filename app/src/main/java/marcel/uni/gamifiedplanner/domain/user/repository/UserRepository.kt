package marcel.uni.gamifiedplanner.domain.user.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievementItem
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserProfile
import marcel.uni.gamifiedplanner.domain.user.model.UserSettings
import marcel.uni.gamifiedplanner.domain.user.model.UserStats


interface UserRepository {
    fun observeUser(uid: String): Flow<User>
    fun observeSettings(userId: String): Flow<UserSettings>
    fun observeStats(userId: String): Flow<UserStats>
    fun observeProfile(userId: String): Flow<UserProfile>
    fun observeInventoryItems(userId: String): Flow<List<UserInventoryItem>>
    fun observeAchievementItems(userId: String): Flow<List<UserAchievementItem>>
    fun observeHistoryItems(userId: String): Flow<List<TaskHistoryItem>>
    suspend fun create(uid: String, username: String, email: String)
    suspend fun addXp(uid: String, amount: Long)
    suspend fun updateSettings(uid: String, new: UserSettings)
    suspend fun updateProfile(uid: String, new: UserProfile)
    suspend fun purchaseItem(uid: String, itemId: String, cost: Long): Result<Unit>
}
