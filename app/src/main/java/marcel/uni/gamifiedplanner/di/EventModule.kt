package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.achievement.event.AchievementEventHandler
import marcel.uni.gamifiedplanner.domain.services.EventPublisherService
import org.koin.dsl.module

class EventModule {
    val eventModule = module{
        single{ AchievementEventHandler(get(), get()) }
       single{ EventPublisherService(get()) }
    }
}