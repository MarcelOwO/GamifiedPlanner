package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.calculateLevel

class ObserveLevelUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger,
) {
    operator fun invoke(): Flow<Int> {
        logger.i("Invoking observe level usecase")
        val userId = authRepo.currentUserId
        if (userId == null) {
            logger.e("Invoking observe level usecase requires user to be logged in")
            return flowOf(0)
        }
        logger.i("Invoking observe level usecase was successful")
        return userRepo
            .observeStats(userId)
            .map { stats ->
                calculateLevel(stats.xp)
            }.distinctUntilChanged()
    }
}
