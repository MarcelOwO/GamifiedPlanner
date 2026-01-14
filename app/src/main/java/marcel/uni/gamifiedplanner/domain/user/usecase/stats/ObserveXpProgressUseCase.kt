package marcel.uni.gamifiedplanner.domain.user.usecase.stats

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.calculateProgress

class ObserveXpProgressUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {

    operator fun invoke(): Flow<Long> {
        logger.i("Invoking observe xp progress usecase")
        val userId = authRepo.currentUserId
        if (userId == null) {
            logger.i("Invoking observe xp progress usecase requires user to be logged in")
            return flowOf(0)
        }
        logger.i("Invoking observe xp progress usecase was successful")
        return userRepo.observeStats(userId).map { stats ->
            calculateProgress(stats.xp)
        }
    }
}