package marcel.uni.gamifiedplanner.domain.auth.usecase.register

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository

class RegisterUseCase(
    private val source: FirebaseAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): RegisterResult {

        if (email.isEmpty()) {
            return RegisterResult.ValidationError("Email cannot be empty")
        }
        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            return RegisterResult.ValidationError("Invalid email format")
        }
        if (password.isEmpty()) {
            return RegisterResult.ValidationError("Password cannot be empty")
        }

        source.register(email, password)

        return RegisterResult.Success
    }
}