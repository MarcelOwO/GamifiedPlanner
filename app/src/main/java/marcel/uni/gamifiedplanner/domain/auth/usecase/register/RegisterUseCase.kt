package marcel.uni.gamifiedplanner.domain.auth.usecase.register

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class RegisterUseCase(
    private val source: FirebaseAuthRepository,
    private val userRepo: UserRepository,
    private val logger: AppLogger
) {
    suspend operator fun invoke(
        email: String,
        username: String,
        password: String
    ): PlannerResult<Unit> {
        logger.i("invoking register usecase")

        if (email.isEmpty()) {
            logger.e("Email cannot be empty")
            return PlannerResult.Error("Email cannot be empty")
        }

        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            logger.e("Invalid email format")
            return PlannerResult.Error("Invalid email format")
        }

        if (username.isEmpty()) {
            logger.e("Username cannot be empty")
            return PlannerResult.Error("Username cannot be empty")
        }

        if (password.isEmpty()) {
            logger.e("Password cannot be empty")
            return PlannerResult.Error("Password cannot be empty")
        }

        val id =
            source.register(email, password) ?: return PlannerResult.Error("Registration failed")

        userRepo.create(id, username, email)

        return PlannerResult.Success(Unit)
    }
}