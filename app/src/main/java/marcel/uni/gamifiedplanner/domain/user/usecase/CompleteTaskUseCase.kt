package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.achievement.service.AchievementEngine
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
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
    private val achievementEngine: AchievementEngine
) {
    suspend operator fun invoke(taskId: String): PlannerResult<Unit> {
        val userId = authRepo.currentUserId ?: return PlannerResult.Error("User is not logged in")

        val tasks  = taskRepo.observeTasks(taskId).first()

        if (tasks.isEmpty()){
            return PlannerResult.Error("Task not found")
        }

        val task  = tasks.first()

        val taskHistoryItem = TaskHistoryItem(
            id = UUID.randomUUID().toString(),
            taskId = taskId,
            taskTitle = task.title,
            taskPriority = task.priority,
        )

        val gainedXp = calculateXp(task.priority)

        userRepo.addXp(userId, gainedXp)
        userRepo.addTaskHistoryItem(userId, taskHistoryItem)
        taskRepo.deleteTask(userId,taskId)

        achievementEngine.checkAchievements(userId)

        return PlannerResult.Success(Unit)
    }



}