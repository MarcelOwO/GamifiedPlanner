package marcel.uni.gamifiedplanner.domain.task.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class ObserveTodaysTasksUseCase(

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

        return repo.observeTasks(userId).map { tasks ->

            val today = LocalDate.now(ZoneId.systemDefault())
            tasks.filter { task ->
                val taskDate = Instant.ofEpochMilli(task.startTime.toDate().time)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                taskDate == today
            }
        }
    }
}