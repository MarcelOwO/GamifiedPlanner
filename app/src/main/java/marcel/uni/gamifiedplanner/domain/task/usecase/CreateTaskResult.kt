package marcel.uni.gamifiedplanner.domain.task.usecase

sealed class CreateTaskResult {
    object Success : CreateTaskResult()
    data class ValidationError(val message: String) : CreateTaskResult()
    data class Failure(val error: Throwable) : CreateTaskResult()
}