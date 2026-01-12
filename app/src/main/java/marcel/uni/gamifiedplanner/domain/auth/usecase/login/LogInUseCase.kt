package marcel.uni.gamifiedplanner.domain.auth.usecase.login

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class LogInUseCase(
    private val source: FirebaseAuthRepository,
    private val userRepo: UserRepository

) {
    suspend operator fun invoke(email: String, password: String): PlannerResult<Unit> {
        if (email.isEmpty()) {
            return PlannerResult.Error("Email cannot be empty")
        }
        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            return PlannerResult.Error("Invalid email format")
        }
        if (password.isEmpty()) {
            return PlannerResult.Error("Password cannot be empty")
        }

        val userId = source.login(email, password) ?: return PlannerResult.Error("Login failed")

        userRepo.setLastLogin(userId)


        return PlannerResult.Success(Unit)
    }


}