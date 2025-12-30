package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveXpUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
) {
    suspend operator fun invoke(): Flow<Long> {
        val userId = authRepo.currentUserId

        if (userId == null) {
            return flowOf(0)
        }

        return userRepo.observeStats(userId).map { stats ->
            stats.xp
        }
    }
}
