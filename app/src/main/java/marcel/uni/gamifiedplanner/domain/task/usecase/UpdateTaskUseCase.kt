package marcel.uni.gamifiedplanner.domain.task.usecase

import marcel.uni.gamifiedplanner.data.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus

class UpdateTaskUseCase(private val repo: TaskRepository) {
    suspend operator fun invoke(
        id: String,
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
    ): UpdateTaskResult {

        if (title.isEmpty()) {
            return UpdateTaskResult.ValidationError("Title cannot be empty")
        }
        if (description.isEmpty()) {
            return UpdateTaskResult.ValidationError("Description cannot be empty")
        }
        if (title.length > 50) {
            return UpdateTaskResult.ValidationError("Title cannot be longer than 50 characters")
        }
        if (description.length > 200) {
            return UpdateTaskResult.ValidationError("Description cannot be longer than 200 characters")
        }

        val task = Task(
            id = id,
            title = title,
            description = description,
            priority = priority,
            status = status,
        )

        repo.updateTask(task)
        return UpdateTaskResult.Success
    }
}