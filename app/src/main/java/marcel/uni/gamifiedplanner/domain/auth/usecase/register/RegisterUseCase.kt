package marcel.uni.gamifiedplanner.domain.auth.usecase.register

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class RegisterUseCase(
    private val source: FirebaseAuthRepository,
    private val userRepo: UserRepository
) {
    suspend operator fun invoke(email: String, username:String, password: String): RegisterResult {

        if (email.isEmpty()) {
            return RegisterResult.ValidationError("Email cannot be empty")
        }

        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            return RegisterResult.ValidationError("Invalid email format")
        }

        if(username.isEmpty()) {
            return RegisterResult.ValidationError("Username cannot be empty")
        }

        if (password.isEmpty()) {
            return RegisterResult.ValidationError("Password cannot be empty")
        }

        source.register(email, password)

        userRepo.createUserProfile(username)

        return RegisterResult.Success
    }
}