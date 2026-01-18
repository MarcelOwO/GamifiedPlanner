package marcel.uni.gamifiedplanner.domain.user.usecase.settings

import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class SetDarkModeUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {

    suspend operator fun invoke(enabled: Boolean): PlannerResult<Unit> {
        logger.i("Invoking set dark mode usecase")

        val userId = authRepo.currentUserId

        if (userId == null) {

            logger.e("Invoking set dark mode usecase requires user to be logged in")
            return PlannerResult.Error("User was not logged in")
        }

        val currentSettings = userRepo.observeSettings(userId).first()

        val copy = currentSettings.copy(darkMode = enabled)

        userRepo.updateSettings(userId, copy)

        logger.i("Invoking set dark mode usecase was successful")

        return PlannerResult.Success(Unit)
    }
}
