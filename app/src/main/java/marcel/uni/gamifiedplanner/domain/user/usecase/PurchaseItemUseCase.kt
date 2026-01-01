package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.events.DomainEvent
import marcel.uni.gamifiedplanner.domain.events.DomainEventPublisher
import marcel.uni.gamifiedplanner.domain.services.EventPublisherService
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class PurchaseItemUseCase(
    private val userRepo: UserRepository,
    private val shopRepo: ShopRepository,
    private val eventService: DomainEventPublisher,
    private val authRepo: FirebaseAuthRepository,
) {
    suspend operator fun invoke(itemId: String): PlannerResult<Nothing> {
        val userId = authRepo.currentUserId

        if (userId == null) {
            return PlannerResult.ValidationError("User is not logged in")
        }

        val items = shopRepo.observeShopItems().first()

        val item = items.find { it.id == itemId }

        if (item == null) {
            return PlannerResult.ValidationError("Item does not exist")
        }

        userRepo.purchaseItem(userId, itemId, item.price)

        val event = DomainEvent.ItemPurchasedEvent(itemId)

        eventService.publish(event)

        return PlannerResult.Success<Nothing>()
    }
}
