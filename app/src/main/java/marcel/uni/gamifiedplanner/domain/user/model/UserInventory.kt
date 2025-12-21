package marcel.uni.gamifiedplanner.domain.user.model

data class UserInventory(
    val itemIds: Set<String>,
    val achievements: Map<String, Long>,
    val tasks: Map<String, TaskHistoryItem>
)
