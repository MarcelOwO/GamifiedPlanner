package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class ObserveDarkModeUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger,
) {
    operator fun invoke(): Flow<Boolean> {
        logger.i("Invoking observe dark mode usecase")
        val userId = authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking observe dark mode usecase requires user to be logged in")
            return flowOf(false)
        }

        logger.i("Invoking observe dark mode usecase was successful")

        return userRepo
            .observeSettings(userId)
            .map { settings ->
                settings.darkMode
            }.distinctUntilChanged()
    }
}
