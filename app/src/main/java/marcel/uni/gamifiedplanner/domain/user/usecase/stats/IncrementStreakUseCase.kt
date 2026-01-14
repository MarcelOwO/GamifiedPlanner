package marcel.uni.gamifiedplanner.domain.user.usecase.stats

import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class IncrementStreakUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {

    suspend operator fun invoke(): PlannerResult<Unit> {
        return runCatching {
            val userId = authRepo.currentUserId

            if (userId == null) {
                logger.e("Invoking increment streak usecase requires user to be logged in")
                return PlannerResult.Error("User not logged in")
            }

            val currentStats = userRepo.observeStats(userId).first()

            val newStats = currentStats.copy(
                streak = currentStats.streak + 1
            )

            userRepo.updateStats(userId, newStats)


        }.fold(
            onSuccess = { PlannerResult.Success(Unit) },
            onFailure = { e ->
                logger.e("Error incrementing streak: ${e.message}")
                PlannerResult.Error("${e.message}")
            }
        )

    }
}