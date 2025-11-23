package marcel.uni.gamifiedplanner.domain.usecases.auth

import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource

class LogoutUseCase(
    private val source: FirebaseAuthDataSource
) {
     operator fun invoke() {
        source.logout()
    }
}