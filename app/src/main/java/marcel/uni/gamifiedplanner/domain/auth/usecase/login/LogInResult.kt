package marcel.uni.gamifiedplanner.domain.auth.usecase.login

sealed class LogInResult {
    object Success : LogInResult()
    data class ValidationError(val message: String) : LogInResult()
    data class Failure(val error: Throwable) : LogInResult()
}