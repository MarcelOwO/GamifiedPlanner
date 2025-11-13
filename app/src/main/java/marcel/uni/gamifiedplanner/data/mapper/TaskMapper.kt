package marcel.uni.gamifiedplanner.data.mapper

import marcel.uni.gamifiedplanner.data.model.TaskDto
import marcel.uni.gamifiedplanner.domain.model.Priority
import marcel.uni.gamifiedplanner.domain.model.Task
import marcel.uni.gamifiedplanner.domain.model.TaskStatus

fun TaskDto.toDomain() = Task(
    id = id?:"",
    title = title,
    description = description,
    priority = Priority.valueOf(this.priority),
    status = TaskStatus.valueOf(this.status),
    createdAt = createdAt
)

fun Task.toDto() = TaskDto(

    id = if(id.isNotEmpty()) id else null,
   title = title,
    description = description,
    priority = priority.name,
    status = status.name,
    createdAt =createdAt,
)