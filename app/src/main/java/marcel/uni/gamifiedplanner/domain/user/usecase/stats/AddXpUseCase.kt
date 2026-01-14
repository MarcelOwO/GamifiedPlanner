package marcel.uni.gamifiedplanner.domain.user.usecase.stats

import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class AddXpUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger,
) {
    suspend operator fun invoke(amount: Long): PlannerResult<Unit> {
        logger.i("Invoking add xp usecase with amount: $amount")

        val userId = authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking add xp usecase requires user to be logged in ")
            return PlannerResult.Error("User is not logged in")
        }

        val currentStats = userRepo.observeStats(userId).first()

        val newStats = currentStats.copy(
            xp = currentStats.xp + amount
        )

        userRepo.updateStats(userId, newStats)

        logger.i("Successfully added $amount xp to user $userId")

        return PlannerResult.Success(Unit)
    }
}