package marcel.uni.gamifiedplanner.domain.task.usecase

import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.first
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
        title: String?,
        description: String?,
        priority: Priority?,
        status: TaskStatus?,
        duration: Long?,
        startTime: Timestamp?
    ): PlannerResult<Unit> {
        logger.i("Invoking update task usecase")

        val userId =
            authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking update task requires user to be logged in")
            return PlannerResult.Error("User is not logged in")
        }

        if (status != null && status == TaskStatus.DONE) {
            logger.i("Task is done, invoking complete task usecase")
            completeUseCase(id)
            return PlannerResult.Success(Unit)
        }

        val existing = repo.observeTasks(userId).first().find { it.id == id }

        if (existing == null) {
            logger.e("Task with id $id does not exist for user $userId")
            return PlannerResult.Error("Task does not exist")
        }

        val mergedTitle = title ?: existing.title
        val mergedDescription = description ?: existing.description
        val mergedPriority = priority ?: existing.priority
        val mergedStatus = status ?: existing.status
        val mergedDuration = duration ?: existing.duration
        val mergedStartTime = startTime ?: existing.startTime



        if (mergedTitle.isNullOrBlank()) {
            logger.e("Title cannot be empty inside update task usecase")
            return PlannerResult.Error("Title cannot be empty")
        }

        if (mergedTitle.length > 50) {
            logger.e("Title cannot be longer than 50 characters inside update task usecase")
            return PlannerResult.Error("Title cannot be longer than 50 characters")
        }
        if (mergedDescription.length > 200) {
            logger.e("Description cannot be longer than 200 characters inside update task usecase")
            return PlannerResult.Error("Description cannot be longer than 200 characters")
        }


        val task =
            Task(
                id = id,
                title = mergedTitle,
                description = mergedDescription,
                priority = mergedPriority,
                status = mergedStatus,
                duration = mergedDuration,
                startTime = mergedStartTime
            )

        repo.updateTask(userId, task)
        logger.i("Invoking task update usecase was successful")
        return PlannerResult.Success(Unit)
    }
}
