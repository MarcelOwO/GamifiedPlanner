package marcel.uni.gamifiedplanner.domain.user.usecase

import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.util.PlannerResult
import kotlinx.coroutines.flow.first

class SetUserNameUseCase(
    private val userRepo: UserRepository,
    private val authRepo:FirebaseAuthRepository
) {

    suspend operator fun invoke(new:String):PlannerResult<Unit>{

        val userId = authRepo.currentUserId ?: return PlannerResult.Error("User was nott logged in")


        val currentProfile = userRepo.observeProfile(userId).first()

        val copy = currentProfile.copy(username=new)

        userRepo.updateProfile(userId,copy)

        return PlannerResult.Success(Unit)

    }
}
