package marcel.uni.gamifiedplanner.domain.auth.usecase.login

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class LogInUseCase(
    private val source: FirebaseAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): PlannerResult<Nothing> {
        if (email.isEmpty()) {
            return PlannerResult.ValidationError("Email cannot be empty")
        }
        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            return PlannerResult.ValidationError("Invalid email format")
        }
        if (password.isEmpty()) {
            return PlannerResult.ValidationError("Password cannot be empty")
        }

        source.login(email, password)
        return PlannerResult.Success()
    }


}