package marcel.uni.gamifiedplanner.domain.events

interface DomainEventHandler {
    val order: Int
    suspend fun handle(event:DomainEvent)
}