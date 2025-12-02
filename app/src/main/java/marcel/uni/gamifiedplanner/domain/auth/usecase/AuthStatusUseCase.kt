package marcel.uni.gamifiedplanner.domain.auth.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository

class AuthStatusUseCase(
    private val repo: FirebaseAuthRepository
) {
    operator fun invoke(): Flow<Boolean> = repo.observerAuthState()
}