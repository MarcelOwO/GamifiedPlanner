package marcel.uni.gamifiedplanner.domain.user.repository

import com.google.firebase.firestore.Transaction
import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievement
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserSettings
import marcel.uni.gamifiedplanner.util.PlannerResult

interface UserRepository {
    fun observeUser(uid: String): Flow<User?>

    fun observeInventory(uid: String): Flow<List<UserInventoryItem>>

    suspend fun addXp(
        uid: String,
        amount: Long,
    )

    suspend fun purchaseItem(
        uid: String,
        itemId: String,
        cost: Long,
    ): Result<Transaction?>


    fun observeAchievementsProgress(userId: String): Flow<List<UserAchievement>

    fun observeSettings(userId: String): Flow<UserSettings>

    fun updateSettings(
        userId: String,
        new: UserSettings,
    )
}
