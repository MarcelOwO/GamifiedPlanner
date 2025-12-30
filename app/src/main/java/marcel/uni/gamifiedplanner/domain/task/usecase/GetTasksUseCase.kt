package marcel.uni.gamifiedplanner.domain.task.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.util.PlannerResult
import kotlin.collections.emptyList

class GetTasksUseCase(
    private val repo: TaskRepository,
    private val authRepo: FirebaseAuthRepository
) {

    operator fun invoke(): Flow<List<Task>> {
        val userId =
            authRepo.currentUserId;

        //replace later with proper return message
        if (userId == null) {
            return flowOf(emptyList())
        }

        return repo.observeTasks(userId)
    }
}