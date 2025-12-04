package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.achievement.event.AchievementEventHandler
import marcel.uni.gamifiedplanner.domain.events.DomainEventHandler
import marcel.uni.gamifiedplanner.domain.events.DomainEventPublisher
import marcel.uni.gamifiedplanner.domain.services.EventPublisherService
import org.koin.dsl.module

class EventModule {
    val eventModule = module{
        single<DomainEventHandler>{ AchievementEventHandler(get(), get()) }
        single<DomainEventPublisher>{ EventPublisherService( getAll()) }
    }
}