package marcel.uni.gamifiedplanner.domain.user.usecase

import marcel.uni.gamifiedplanner.domain.events.DomainEvent
import marcel.uni.gamifiedplanner.domain.services.EventPublisherService
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class PurchaseItemUseCase(
    private val userRepo: UserRepository,
    private val shopRepo: ShopRepository,
    private val eventService: EventPublisherService
    ) {

    suspend operator fun invoke(itemId:String){

       userRepo.purchaseItem(itemId);

        val event = DomainEvent.ItemPurchasedEvent(itemId)
        eventService.publish(event)







    }



}