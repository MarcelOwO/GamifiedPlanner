package marcel.uni.gamifiedplanner.data.task.dto

import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus

data class TaskDto(
    val id: String = "",
    val title: String = "",
    val description: String? = null,
    val priority: Priority = Priority.MEDIUM,
    val status: TaskStatus = TaskStatus.OPEN,
    val createdAt: Long = 0L,
)