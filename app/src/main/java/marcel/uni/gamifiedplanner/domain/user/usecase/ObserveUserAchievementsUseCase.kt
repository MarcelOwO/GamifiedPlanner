package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievementItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveUserAchievementsUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger

) {

    operator fun invoke(): Flow<List<UserAchievementItem>> {
        logger.i("Invoking observe user achievements usecase")
        val userId = authRepo.currentUserId
        if (userId == null) {
            logger.e("Invoking observe user achievements usecase requires user to be logged in")
            return flowOf(emptyList())
        }
        logger.i("Invoking observe user achievements usecase was successful")
        return userRepo.observeAchievementItems(userId)

    }
}
