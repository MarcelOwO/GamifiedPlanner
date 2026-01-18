package marcel.uni.gamifiedplanner.domain.user.usecase.stats

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveStreakUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {

    operator fun invoke(): Flow<Long> {

        val userId = authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking observe streak usecase requires user to be logged in")
            return flowOf(0L)
        }

        return userRepo.observeStats(userId).map {
            it.streak
        }

    }
}