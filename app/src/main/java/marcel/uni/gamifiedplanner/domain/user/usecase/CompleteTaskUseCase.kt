package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.achievement.service.AchievementEngine
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult
import marcel.uni.gamifiedplanner.util.calculateXp
import java.util.UUID

class CompleteTaskUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val taskRepo: TaskRepository,
    private val achievementEngine: AchievementEngine,
    private val logger: AppLogger,
) {
    suspend operator fun invoke(taskId: String): PlannerResult<Unit> {
        logger.i("Invoking complete task usecase for taskId: $taskId")

        val userId = authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking complete task usecase requires user to be logged in")
            return PlannerResult.Error("User is not logged in")
        }

        val tasks = taskRepo.observeTasks(userId).first()

        var currentTask = tasks.find { it.id == taskId }

        if (currentTask == null) {
            logger.e("Task with id $taskId not found for user $userId")
            return PlannerResult.Error("Task not found")
        }

        val taskHistoryItem =
            TaskHistoryItem(
                id = UUID.randomUUID().toString(),
                taskId = taskId,
                taskTitle = currentTask.title,
                taskPriority = currentTask.priority,
            )

        val gainedXp = calculateXp(currentTask.priority)

        userRepo.addXp(userId, gainedXp)
        userRepo.addTaskHistoryItem(userId, taskHistoryItem)
        taskRepo.deleteTask(userId, taskId)

        achievementEngine.checkAchievements(userId)

        logger.i("Completed task with id $taskId for user $userId, gained $gainedXp xp")

        return PlannerResult.Success(Unit)
    }
}
