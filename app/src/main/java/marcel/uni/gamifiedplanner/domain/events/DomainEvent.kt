package marcel.uni.gamifiedplanner.domain.events

sealed class DomainEvent {
    data class ItemPurchasedEvent(val userId:String,val itemId:String):DomainEvent()
    data class TaskCompletedEvent(val userId:String,val taskId:String):DomainEvent()
}