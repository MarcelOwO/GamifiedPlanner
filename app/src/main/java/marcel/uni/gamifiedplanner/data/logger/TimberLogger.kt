package marcel.uni.gamifiedplanner.data.logger

import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import timber.log.Timber

class TimberLogger : AppLogger {

    override fun i(message: String) = Timber.i(message)

    override fun e(message: String) = Timber.e(message)
}