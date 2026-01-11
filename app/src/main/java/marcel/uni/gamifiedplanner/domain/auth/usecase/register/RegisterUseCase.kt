package marcel.uni.gamifiedplanner.domain.auth.usecase.register

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class RegisterUseCase(
    private val source: FirebaseAuthRepository,
    private val userRepo: UserRepository
) {
    suspend operator fun invoke(email: String, username:String, password: String): PlannerResult<Unit> {

        if (email.isEmpty()) {
            return PlannerResult.Error("Email cannot be empty")
        }

        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            return PlannerResult.Error("Invalid email format")
        }

        if(username.isEmpty()) {
            return PlannerResult.Error("Username cannot be empty")
        }

        if (password.isEmpty()) {
            return PlannerResult.Error("Password cannot be empty")
        }

        val id =
            source.register(email, password) ?: return PlannerResult.Error("Registration failed")

        userRepo.create(id,username,email)

        return PlannerResult.Success(Unit)
    }
}