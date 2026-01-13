package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveNotificationStateUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger,
) {
    operator fun invoke(): Flow<Boolean> {
        logger.i("Invoking observe notification state usecase")
        val userId = authRepo.currentUserId
        if (userId == null) {
            logger.e("Invoking observe notification state usecase requires user to be logged in")
            return flowOf(false)
        }
        logger.i("Invoking observe notification state usecase was successful")
        return userRepo.observeSettings(userId).map { settings ->
            settings.notificationState
        }
    }
}
