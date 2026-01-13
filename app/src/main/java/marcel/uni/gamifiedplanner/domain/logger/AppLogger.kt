package marcel.uni.gamifiedplanner.domain.logger

interface AppLogger {
    fun i(message: String)

    fun e(message: String)
}
