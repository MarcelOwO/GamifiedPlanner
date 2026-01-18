package marcel.uni.gamifiedplanner.domain.task.usecase

import com.google.firebase.Timestamp
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.notifications.TaskScheduler
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class CreateTaskUseCase(
    private val repository: TaskRepository,
    private val authRepo: FirebaseAuthRepository,
    private val taskScheduler: TaskScheduler,
    private val logger: AppLogger
) {
    suspend operator fun invoke(
        title: String,
        priority: Priority,
        description: String,
        status: TaskStatus,
        duration: Long,
        startTime: Timestamp,
    ): PlannerResult<Unit> {
        logger.i("Invoking create task usecase")
        val userId =
            authRepo.currentUserId ?: return PlannerResult.Error("User is not logged in")

        if (title.isEmpty()) {
            logger.e("Title cannot be empty inside create task usecase")
            return PlannerResult.Error("Title cannot be empty")
        }

        if (title.length > 50) {
            logger.e("Title cannot be longer than 50 characters inside create task usecase")
            return PlannerResult.Error("Title cannot be longer than 50 characters")
        }

        if (description.length > 200) {
            logger.e("Description cannot be longer than 200 characters inside create task usecase")
            return PlannerResult.Error("Description cannot be longer than 200 characters")
        }

        val task = Task(
            title = title,
            description = description,
            priority = priority,
            status = status,
            startTime = startTime,
            duration = duration
        )

        repository.createTask(userId, task)
        taskScheduler.scheduleTask(task)
        logger.i("Task created successfully")
        return PlannerResult.Success(Unit)
    }

}