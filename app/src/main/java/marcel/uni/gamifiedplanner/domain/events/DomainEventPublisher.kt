package marcel.uni.gamifiedplanner.domain.events

interface DomainEventPublisher {
    suspend fun publish(event:DomainEvent)
}