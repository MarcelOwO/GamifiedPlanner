package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger

class ObserveUserUsernameUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {
    operator fun invoke(): Flow<String> {
        logger.i("Invoking observe user username usecase")
        val userId = authRepo.currentUserId
        if (userId == null) {
            logger.e("Invoking observe user username usecase requires user to be logged in")
            return flowOf("")
        }
        logger.i("Invoking observe user username usecase was successful")
        return userRepo.observeProfile(userId).map { profile ->
            profile.username
        }
    }
}
