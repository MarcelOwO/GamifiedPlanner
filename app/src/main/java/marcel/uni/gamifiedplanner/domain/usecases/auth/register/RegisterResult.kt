package marcel.uni.gamifiedplanner.domain.usecases.auth.register

sealed class RegisterResult {
    object Success : RegisterResult()
    data class ValidationError(val message: String) : RegisterResult()
    data class Failure(val error: Throwable) : RegisterResult()
}