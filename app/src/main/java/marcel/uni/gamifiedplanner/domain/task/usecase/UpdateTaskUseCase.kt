package marcel.uni.gamifiedplanner.domain.task.usecase

import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.user.usecase.tasks.CompleteTaskUseCase
import marcel.uni.gamifiedplanner.util.PlannerResult

class UpdateTaskUseCase(
    private val repo: TaskRepository,
    private val authRepo: FirebaseAuthRepository,
    private val completeUseCase: CompleteTaskUseCase,
    private val logger: AppLogger,
) {
    suspend operator fun invoke(
        id: String,
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
    ): PlannerResult<Unit> {
        logger.i("Invoking update task usecase")

        val userId =
            authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking update task requires user to be logged in")
            return PlannerResult.Error("User is not logged in")
        }

        if (title.isEmpty()) {
            logger.e("Title cannot be empty inside update task usecase")
            return PlannerResult.Error("Title cannot be empty")
        }

        if (title.length > 50) {
            logger.e("Title cannot be longer than 50 characters inside update task usecase")
            return PlannerResult.Error("Title cannot be longer than 50 characters")
        }
        if (description.length > 200) {
            logger.e("Description cannot be longer than 200 characters inside update task usecase")
            return PlannerResult.Error("Description cannot be longer than 200 characters")
        }

        if (status == TaskStatus.DONE) {
            logger.i("Task is done, invoking complete task usecase")
            completeUseCase(id)
            return PlannerResult.Success(Unit)
        }

        val task =
            Task(
                id = id,
                title = title,
                description = description,
                priority = priority,
                status = status,
            )

        repo.updateTask(userId, task)
        logger.i("Invoking task update usecase was successful")
        return PlannerResult.Success(Unit)
    }
}
