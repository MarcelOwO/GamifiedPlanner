package marcel.uni.gamifiedplanner.domain.auth.usecase.register

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class RegisterUseCase(
    private val source: FirebaseAuthRepository,
    private val userRepo: UserRepository
) {
    suspend operator fun invoke(email: String, username:String, password: String): PlannerResult<Nothing> {

        if (email.isEmpty()) {
            return PlannerResult.ValidationError("Email cannot be empty")
        }

        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            return PlannerResult.ValidationError("Invalid email format")
        }

        if(username.isEmpty()) {
            return PlannerResult.ValidationError("Username cannot be empty")
        }

        if (password.isEmpty()) {
            return PlannerResult.ValidationError("Password cannot be empty")
        }

        source.register(email, password)

        userRepo.create(username,email)

        return PlannerResult.Success()
    }
}