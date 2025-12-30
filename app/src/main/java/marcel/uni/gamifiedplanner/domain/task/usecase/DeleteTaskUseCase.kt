package marcel.uni.gamifiedplanner.domain.task.usecase

import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class DeleteTaskUseCase(
    private val repo: TaskRepository,
    private val authRepo: FirebaseAuthRepository
) {
    suspend operator fun invoke(taskId: String): PlannerResult {

        val userId =
            authRepo.currentUserId ?: return PlannerResult.ValidationError("User is not logged in");

        repo.deleteTask(userId, taskId)
        return PlannerResult.Success
    }
}