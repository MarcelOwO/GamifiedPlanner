package marcel.uni.gamifiedplanner.domain.user.usecase

import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.util.PlannerResult
import kotlinx.coroutines.flow.first

class SetDarkModeUseCase(
    private val userRepo: UserRepository,
    private val authRepo:FirebaseAuthRepository
) {

    suspend operator fun invoke(enabled:Boolean):PlannerResult<Unit>{

        val userId = authRepo.currentUserId ?: return PlannerResult.Error("User was not logged in")

        val currentSettings = userRepo.observeSettings(userId).first()

        val copy = currentSettings.copy(darkMode=enabled)

        userRepo.updateSettings(userId,copy)

        return PlannerResult.Success(Unit)

    }
}
