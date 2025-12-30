package marcel.uni.gamifiedplanner.util

sealed class PlannerResult<out T> {
    data class Success<out T>(
        val data: T? = null,
    ) : PlannerResult<T>()

    data class ValidationError(
        val message: String,
        val field: String? = null,
    ) : PlannerResult<Nothing>()

    data class Failure(
        val error: Throwable,
    ) : PlannerResult<Nothing>()
}

inline fun <T, R> PlannerResult<T>.mapSuccess(transform: (T?) -> PlannerResult<R>): PlannerResult<R> =
    when (this) {
        is PlannerResult.Success -> transform(data)
        is PlannerResult.ValidationError -> this
        is PlannerResult.Failure -> this
    }
