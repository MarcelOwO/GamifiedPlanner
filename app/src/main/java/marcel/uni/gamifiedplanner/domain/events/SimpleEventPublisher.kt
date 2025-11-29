package marcel.uni.gamifiedplanner.domain.events

class SimpleEventPublisher(private val handlers: List<DomainEventHandler>) : DomainEventPublisher {

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