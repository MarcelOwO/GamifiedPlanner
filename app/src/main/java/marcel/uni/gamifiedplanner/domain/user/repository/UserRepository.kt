package marcel.uni.gamifiedplanner.domain.user.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievement
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem

interface UserRepository {
    suspend fun createUserProfile(username: String)

    fun observeUser(): Flow<User?>

    suspend fun addXp(amoung: Int)

    suspend fun spendCurrency(amount: Int): Result<Unit>

    fun observeAchievementsProgress(): Flow<Map<String, Long>>

    suspend fun unlockAchievement(achivementId: String)

    fun getTaskHistory(
        startTime: Long,
        endTime: Long,
    ): Flow<List<TaskHistoryItem>>

    suspend fun logCompletedTask(task: TaskHistoryItem)

    fun observeInventory(): Flow<List<UserInventoryItem>>

    suspend fun purchaseItem(itemId: String)

    fun observeDarkMode(): Flow<Boolean>

    suspend fun toggleDarkMode(enabled: Boolean)

    fun observeNotifications(): Flow<Boolean>

    suspend fun toggleNotifications(enabled: Boolean)
}
