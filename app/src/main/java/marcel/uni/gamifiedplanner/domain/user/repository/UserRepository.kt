package marcel.uni.gamifiedplanner.domain.user.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievement
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserStats

interface UserRepository {
    fun observeUserStats():Flow<UserStats>
    suspend fun addXp(amoung:Int)
    suspend fun spendCurrency(amount:Int): Result<Unit>

    fun observeAchievementsProgress(): Flow<List<UserAchievement>>
    suspend fun unlockAchievement(achivementId:String)


    fun getTaskHistory(startTime:Long, endTime:Long): Flow<List<TaskHistoryItem>>
    suspend fun logCompletedTask(task: TaskHistoryItem)

    fun observeInventory() : Flow<List<UserInventoryItem>>
    suspend fun purchaseItem(itemId:String)

}