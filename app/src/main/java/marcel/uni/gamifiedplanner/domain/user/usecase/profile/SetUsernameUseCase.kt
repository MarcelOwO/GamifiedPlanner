package marcel.uni.gamifiedplanner.domain.user.usecase.profile

import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class SetUserNameUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {

    suspend operator fun invoke(new: String): PlannerResult<Unit> {
        logger.i("Invoking set username usecase")

        val userId = authRepo.currentUserId
        if (userId == null) {
            logger.e("Invoking set username usecase requires user to be logged in")
            return PlannerResult.Error("User was not logged in")
        }

        val currentProfile = userRepo.observeProfile(userId).first()

        val copy = currentProfile.copy(username = new)

        userRepo.updateProfile(userId, copy)

        logger.i("Invoking set username usecase was successful")

        return PlannerResult.Success(Unit)
    }
}
