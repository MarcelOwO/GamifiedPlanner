package marcel.uni.gamifiedplanner.domain.auth.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource

class AuthStatusUseCase(
    private val repo: FirebaseAuthDataSource
) {


    operator fun invoke(): Flow<Boolean> = repo.observerAuthState()
}