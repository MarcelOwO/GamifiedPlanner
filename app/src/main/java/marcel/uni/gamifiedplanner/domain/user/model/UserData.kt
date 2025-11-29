package marcel.uni.gamifiedplanner.domain.user.model

import marcel.uni.gamifiedplanner.domain.task.model.Task

data class UserData(
    val uid: String = "",
    val username: String = "",
    val level: Int = 1,
    val xp: Int = 0,
    val tasks: List<Task> = emptyList(),
    val boughtItems: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val completedTasks: Int = 0,
)