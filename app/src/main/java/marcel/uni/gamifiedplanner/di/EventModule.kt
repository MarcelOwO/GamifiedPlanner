package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.events.SimpleEventPublisher
import org.koin.dsl.module

class EventModule {
    val eventModule = module{

       single{ SimpleEventPublisher(listOf(get())) }
    }
}