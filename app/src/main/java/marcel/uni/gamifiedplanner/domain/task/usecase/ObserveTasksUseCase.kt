package marcel.uni.gamifiedplanner.domain.task.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository

class ObserveTasksUseCase(
    private val repo: TaskRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {

    operator fun invoke(): Flow<List<Task>> {
        logger.i("Invoking get tasks usecase")

        val userId = authRepo.currentUserId

        if (userId == null) {
            logger.e("UserId is null inside get tasks usecase")
            return flowOf(emptyList())
        }

        logger.i("Get task usecase invoked successfully")

        return repo.observeTasks(userId)
    }
}