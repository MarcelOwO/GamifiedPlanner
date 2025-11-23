package marcel.uni.gamifiedplanner.domain.usecases.task

sealed class CreateTaskResult {
    object Success : CreateTaskResult()
    data class ValidationError(val message: String) : CreateTaskResult()
    data class Failure(val error: Throwable) : CreateTaskResult()
}