package marcel.uni.gamifiedplanner.domain.user.usecase.stats

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveBalanceUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {
    operator fun invoke(): Flow<Long> {
        logger.i("Invoking observe currency usecase")
        val userId = authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking observe currency usecase requires user to be logged in")
            return flowOf(0)
        }

        logger.i("Invoking observe currency usecase was successful")
        return userRepo
            .observeStats(userId)
            .map { stats ->
                stats.currency
            }.catch { e ->
                logger.e("Error observing XP: ${e.message}")
                emit(0)
            }
    }
}