package marcel.uni.gamifiedplanner.domain.usecases.task

sealed class DeleteTaskResult {
    object Success : DeleteTaskResult()
    data class Failure(val error: Throwable) : DeleteTaskResult()
    data class NotFound(val message: String) : DeleteTaskResult()
}