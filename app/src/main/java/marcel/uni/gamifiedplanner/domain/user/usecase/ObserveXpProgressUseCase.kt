package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.calculateProgress

class ObserveXpProgressUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
) {

    operator fun invoke(): Flow<Long> {
        val userId = authRepo.currentUserId ?: return flowOf(0)

        return userRepo.observeStats(userId).map { stats ->
           calculateProgress(stats.xp)
        }
    }
}