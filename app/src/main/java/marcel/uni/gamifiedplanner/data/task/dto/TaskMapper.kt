package marcel.uni.gamifiedplanner.data.task.dto

import marcel.uni.gamifiedplanner.domain.task.model.Task


fun TaskDto.toDomain() = Task(
    id = this.id,
    title = this.title,
    description = this.description,
    duration =  this.duration,
    priority = this.priority,
    status = this.status,
    createdAt = this.createdAt
)


fun Task.toDto() = TaskDto(
    id = this.id,
    title = this.title,
    description = this.description,
    duration = this.duration,
    priority = this.priority,
    status = this.status,
    createdAt = this.createdAt
)