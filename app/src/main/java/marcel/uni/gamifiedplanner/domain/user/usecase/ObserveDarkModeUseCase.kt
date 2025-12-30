package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveDarkModeUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository
) {
    operator fun invoke(): Flow<Boolean> {
        val userId = authRepo.currentUserId;
        if (userId == null){
            return flowOf(false)
        }

        return userRepo.observeDarkMode(userId);
    }
}
