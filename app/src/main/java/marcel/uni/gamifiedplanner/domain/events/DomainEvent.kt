package marcel.uni.gamifiedplanner.domain.events

sealed class DomainEvent {
    data class ItemPurchasedEvent(val itemId:String):DomainEvent()
    data class TaskCompletedEvent(val taskId:String):DomainEvent()
}