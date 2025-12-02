package marcel.uni.gamifiedplanner.domain.auth.usecase

import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository

class LogoutUseCase(
    private val source: FirebaseAuthRepository
) {
     operator fun invoke() {
        source.logout()
    }
}