package marcel.uni.gamifiedplanner.domain.user.model

import marcel.uni.gamifiedplanner.domain.task.model.Priority


data class TaskHistoryItem(
    val id: String,
    val taskId: String,
    val completedAt: Long,
    val taskPriority: Priority,
)
