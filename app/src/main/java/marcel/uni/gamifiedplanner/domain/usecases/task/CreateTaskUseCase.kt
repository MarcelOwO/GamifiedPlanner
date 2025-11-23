package marcel.uni.gamifiedplanner.domain.usecases.task

import marcel.uni.gamifiedplanner.data.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.models.Priority
import marcel.uni.gamifiedplanner.domain.models.Task
import marcel.uni.gamifiedplanner.domain.models.TaskStatus

class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
    ): CreateTaskResult {

        if (title.isEmpty()) {
            return CreateTaskResult.ValidationError("Title cannot be empty")
        }
        if (description.isEmpty()) {
            return CreateTaskResult.ValidationError("Description cannot be empty")
        }
        if (title.length > 50) {
            return CreateTaskResult.ValidationError("Title cannot be longer than 50 characters")
        }
        if (description.length > 200) {
            return CreateTaskResult.ValidationError("Description cannot be longer than 200 characters")
        }

        val task = Task(
            title = title,
            description = description,
            priority = priority,
            status = status,
        )
        repository.createTask(task)
        return CreateTaskResult.Success
    }

}