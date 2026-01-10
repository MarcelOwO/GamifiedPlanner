package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievementItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository

class ObserveUserAchievementsUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository
) {

    operator fun invoke(): Flow<List<UserAchievementItem>> {
        val userId = authRepo.currentUserId ?: return flowOf(emptyList())
        return userRepo.observeAchievementItems(userId)

    }
}
