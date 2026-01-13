package marcel.uni.gamifiedplanner.domain.auth.usecase.login

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class LogInUseCase(
    private val source: FirebaseAuthRepository,
    private val userRepo: UserRepository,
    private val logger: AppLogger
) {
    suspend operator fun invoke(email: String, password: String): PlannerResult<Unit> {
        logger.i("invoking  login usecase")
        if (email.isEmpty()) {
            logger.e("Email cannot be empty")
            return PlannerResult.Error("Email cannot be empty")
        }
        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            logger.e("Invalid email format")
            return PlannerResult.Error("Invalid email format")
        }
        if (password.isEmpty()) {
            logger.e("Password cannot be empty")
            return PlannerResult.Error("Password cannot be empty")
        }

        val userId = source.login(email, password) ?: return PlannerResult.Error("Login failed")

        userRepo.setLastLogin(userId)

        return PlannerResult.Success(Unit)
    }


}