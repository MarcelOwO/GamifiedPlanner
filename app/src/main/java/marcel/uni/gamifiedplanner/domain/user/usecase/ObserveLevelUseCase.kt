package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.distinctUntilChanged
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import kotlin.math.*
import marcel.uni.gamifiedplanner.util.calculateLevel

class ObserveLevelUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
) {
    operator fun invoke(): Flow<Int> {
        val userId = authRepo.currentUserId ?: return flowOf(0)

        return userRepo
            .observeStats(userId)
            .map { stats ->
                calculateLevel(stats.xp)
            }.distinctUntilChanged()
    }
}

