package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.logger.TimberLogger
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class LoggingModule {
    val loggingModule = module {
        singleOf(::TimberLogger) { bind<AppLogger>() }
    }
}