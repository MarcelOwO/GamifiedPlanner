package marcel.uni.gamifiedplanner.domain.user.usecase

import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.util.PlannerResult
import kotlinx.coroutines.flow.first

class SetDarkModeUseCase(
    private val userRepo: UserRepository
    private val authRepo:FirebaseAuthRepository
) {

    suspend operator fun invoke(enabled:Boolean):PlannerResult{

        val userId = authRepo.currentUserId

        if (userId == null){
            return PlannerResult.ValidationError("User was not logged in")
        }

        val currentSettings = userRepo.observeSettings(userId).first()

        var copy = currentSettings.copy(darkMode=enabled)

        userRepo.updateSettings(userId,copy)

        return PlannerResult.Success<Nothing>()

    }
}
