package marcel.uni.gamifiedplanner.domain.user.usecase

import marcel.uni.gamifiedplanner.domain.task.usecase.CreateTaskResult

sealed class PurchaseItemResult {

    object Success : PurchaseItemResult()
    data class ValidationError(val message: String) : PurchaseItemResult()
    data class Failure(val error: Throwable) : PurchaseItemResult()
}