package marcel.uni.gamifiedplanner.domain.auth.usecase

import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger

class LogoutUseCase(
    private val source: FirebaseAuthRepository,
    private val logger: AppLogger
) {
    operator fun invoke() {
        logger.i("Invoking log out usecase")
        source.logout()
    }
}