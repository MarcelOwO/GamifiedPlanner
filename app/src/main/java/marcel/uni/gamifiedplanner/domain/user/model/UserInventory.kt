package marcel.uni.gamifiedplanner.domain.user.model

data class UserInventory(
    val items: List<UserInventoryItem>,
    val achievements: List<UserAchievementItem>,
    val tasks: List<TaskHistoryItem>,
)
