package marcel.uni.gamifiedplanner.domain.user.usecase.tasks

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class ObserveTodaysUsersTaskUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {
    operator fun invoke(): Flow<List<TaskHistoryItem>> {
        logger.i("Invoking observe user task history usecase")
        val userId = authRepo.currentUserId
        if (userId == null) {
            logger.e("Invoking observe user task history usecase requires user to be logged in")
            return flowOf(emptyList())
        }

        logger.i("Invoking observe user task history usecase was successful")

        return userRepo.observeHistoryItems(userId).map { tasks ->
            val today = LocalDate.now(ZoneId.systemDefault())
            tasks.filter { task ->
                val taskDate = Instant.ofEpochMilli(task.completedAt.toDate().time)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                taskDate == today
            }
        }
    }
}
