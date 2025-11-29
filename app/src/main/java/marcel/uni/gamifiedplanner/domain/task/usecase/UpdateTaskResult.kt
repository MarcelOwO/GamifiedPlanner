package marcel.uni.gamifiedplanner.domain.task.usecase

sealed class UpdateTaskResult {
    object Success : UpdateTaskResult()
    data class ValidationError(val message: String) : UpdateTaskResult()
    data class Failure(val error: Throwable) : UpdateTaskResult()


}