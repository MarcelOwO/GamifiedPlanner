package marcel.uni.gamifiedplanner.data.user.dto.mapper

import marcel.uni.gamifiedplanner.data.user.dto.TaskLogDto
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem

fun TaskHistoryItem.ToDto() =
    TaskLogDto(
       id=  this.id,
       taskId = this.taskId,
       timestamp = this.completedAt,
       priority = this.taskPriority.toString()
    )

fun TaskLogDto.ToDomain()=
    TaskHistoryItem(
        id = this.id,
        taskId = this.taskId,
        completedAt = this.timestamp,
        taskPriority = Priority.valueOf(this.priority)
    )