package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class SetNotificationStateUseCase(
    private val userRepo: UserRepository,
) {
    suspend operator fun invoke(enabled: Boolean): PlannerResult {
        val userId = authRepo.currentUserId

        if (userId == null) {
            return PlannerResult.ValidationError("User was not logged in")
        }

        val currentSettings = userRepo.observeSettings(userId).first()

        var copy = currentSettings.copy(notificationState = enabled)

        userRepo.updateSettings(userId, copy)

        return PlannerResult.Success<Nothing>()
    }
}
