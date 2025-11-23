package marcel.uni.gamifiedplanner.domain.usecases.task

sealed class UpdateTaskResult {
    object Success : UpdateTaskResult()
    data class ValidationError(val message: String) : UpdateTaskResult()
    data class Failure(val error: Throwable) : UpdateTaskResult()


}