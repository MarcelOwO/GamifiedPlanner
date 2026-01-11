package marcel.uni.gamifiedplanner.domain.task.usecase

import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus
import marcel.uni.gamifiedplanner.util.PlannerResult

class CreateTaskUseCase(
    private val repository: TaskRepository,
    private val authRepo : FirebaseAuthRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
    ): PlannerResult<Unit> {
        val userId =
            authRepo.currentUserId ?: return PlannerResult.Error("User is not logged in");

        if (title.isEmpty()) {
            return PlannerResult.Error("Title cannot be empty")
        }
        if (description.isEmpty()) {
            return PlannerResult.Error("Description cannot be empty")
        }
        if (title.length > 50) {
            return PlannerResult.Error("Title cannot be longer than 50 characters")
        }
        if (description.length > 200) {
            return PlannerResult.Error("Description cannot be longer than 200 characters")
        }

        val task = Task(
            title = title,
            description = description,
            priority = priority,
            status = status,
        )

        repository.createTask(userId,task)
        return PlannerResult.Success(Unit)
    }

}