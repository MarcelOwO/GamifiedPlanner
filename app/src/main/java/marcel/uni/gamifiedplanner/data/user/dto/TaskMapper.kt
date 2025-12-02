package marcel.uni.gamifiedplanner.data.user.dto

import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import kotlin.toString

fun TaskLogDto.toDomain()= TaskHistoryItem(
    uuid = this.id,
    taskId = this.uid,
    completedAt = this.timestamp,
    taskPriority = Priority.valueOf(this.priority),
)

fun TaskHistoryItem.toDto()= TaskLogDto(
    id = this.uuid,
    uid = this.taskId,
    timestamp = this.completedAt,
    priority = this.taskPriority.toString(),
)
