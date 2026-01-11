package marcel.uni.gamifiedplanner.util

// extension methods for simplifiying error handling

sealed class PlannerResult<out T> {
    data class Success<out T>(
        val data: T,
    ) : PlannerResult<T>()

    data class Error(
        val message: String,
        val cause: Throwable? = null,
    ) : PlannerResult<Nothing>()

    val isSuccess: Boolean get() = this is Success

    fun getOrNull(): T? = (this as? Success)?.data
}

inline fun <T> PlannerResult<T>.onFailure(action: (PlannerResult.Error) -> Unit): PlannerResult<T> {
    if (this is PlannerResult.Error) {
        action(this)
    }
    return this
}

