package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.events.DomainEvent
import marcel.uni.gamifiedplanner.domain.events.DomainEventPublisher
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class PurchaseItemUseCase(
    private val userRepo: UserRepository,
    private val shopRepo: ShopRepository,
    private val authRepo: FirebaseAuthRepository,
) {
    suspend operator fun invoke(itemId: String): PlannerResult<Unit> {
        val userId = authRepo.currentUserId ?: return PlannerResult.Error("User is not logged in")

        val items = shopRepo.observeShopItems().first()

        val item =
            items.find { it.id == itemId } ?: return PlannerResult.Error("Item does not exist")


        userRepo.purchaseItem(userId, itemId, item.price)

        return PlannerResult.Success(Unit)
    }
}
