package marcel.uni.gamifiedplanner.domain.models

data class UserData(
    val uid: String = "",
    val username: String = "",
    val xp: Int = 0,
    val tasks: List<Task> = emptyList(),
    val boughtItems: List<Int> = emptyList(),
    val achievements: List<String> = emptyList(),
    val completedTasks: Int = 0,
)