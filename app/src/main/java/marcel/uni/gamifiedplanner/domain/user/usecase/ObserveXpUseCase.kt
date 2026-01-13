package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveXpUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger,
) {
    operator fun invoke(): Flow<Long> {
        logger.i("Invoking observe xp usecase")
        val userId = authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking observe xp usecase requires user to be logged in")
            return flowOf(0)
        }

        logger.i("Invoking observe xp usecase was successful")
        return userRepo
            .observeStats(userId)
            .map { stats ->
                stats.xp
            }.catch { e ->
                logger.e("Error observing XP: ${e.message}")
                emit(0)
            }
    }
}
