package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveNotificationStateUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
) {
    operator fun invoke(): Flow<Boolean> {
        val userId = authRepo.currentUserId ?: return flowOf(false)

        return userRepo.observeSettings(userId).map { settings ->
            settings.notificationState
        }
    }
}
