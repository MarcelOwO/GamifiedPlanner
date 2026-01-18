package marcel.uni.gamifiedplanner.domain.task.usecase

import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class DeleteTaskUseCase(
    private val repo: TaskRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {
    suspend operator fun invoke(taskId: String): PlannerResult<Unit> {
        logger.i("Invoking delete task usecase")

        val userId =
            authRepo.currentUserId ?: return PlannerResult.Error("User is not logged in")

        repo.deleteTask(userId, taskId)

        logger.i("Task deleted usecase successfully invoked")

        return PlannerResult.Success(Unit)
    }
}