package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class AddXpUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
) {
    suspend operator fun invoke(amount: Long): PlannerResult<Nothing> {
        val userId = authRepo.currentUserId

        if (userId == null) {
            return PlannerResult.ValidationError("User is not logged in")
        }

        userRepo.addXp(userId, amount)

        return PlannerResult.Success()
    }
}
