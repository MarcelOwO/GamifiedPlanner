package marcel.uni.gamifiedplanner.domain.services

import marcel.uni.gamifiedplanner.domain.events.DomainEvent
import marcel.uni.gamifiedplanner.domain.events.DomainEventHandler
import marcel.uni.gamifiedplanner.domain.events.DomainEventPublisher

class EventPublisherService (private val handlers: List<DomainEventHandler>) :
    DomainEventPublisher {

    override suspend fun publish(event: DomainEvent) {

        handlers.sortedBy { it.order }.forEach { handler ->
            try {
                handler.handle(event)
            } catch (t: Throwable) {
                //will implement later
            }
        }
    }
}