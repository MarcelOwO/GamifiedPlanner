package marcel.uni.gamifiedplanner.domain.task.usecase

sealed class DeleteTaskResult {
    object Success : DeleteTaskResult()
    data class Failure(val error: Throwable) : DeleteTaskResult()
    data class NotFound(val message: String) : DeleteTaskResult()
}