package marcel.uni.gamifiedplanner.domain.auth.usecase.login

import android.util.Patterns.EMAIL_ADDRESS
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource

class LogInUseCase(
    private val source: FirebaseAuthDataSource
) {
    suspend operator fun invoke(email: String, password: String): LogInResult {
        if (email.isEmpty()) {
            return LogInResult.ValidationError("Email cannot be empty")
        }
        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
            return LogInResult.ValidationError("Invalid email format")
        }
        if (password.isEmpty()) {
            return LogInResult.ValidationError("Password cannot be empty")
        }

        //add other checks but whatever
        //this is good enough for now

        source.login(email, password)
        return LogInResult.Success
    }


}