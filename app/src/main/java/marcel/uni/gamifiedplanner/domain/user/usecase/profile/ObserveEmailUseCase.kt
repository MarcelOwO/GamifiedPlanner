package marcel.uni.gamifiedplanner.domain.user.usecase.profile

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class ObserveEmailUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {
    operator fun invoke(): Flow<String> {
        logger.i("Invoking observe user email usecase")
        val userId = authRepo.currentUserId
        if (userId == null) {
            logger.e("Invoking observe user email usecase requires user to be logged in")
            return flowOf("")
        }
        logger.i("Invoking observe user email usecase was successful")
        return userRepo.observeProfile(userId).map { profile ->
            profile.email
        }
    }
}