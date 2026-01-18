package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class PurchaseItemUseCase(
    private val userRepo: UserRepository,
    private val shopRepo: ShopRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {
    suspend operator fun invoke(itemId: String): PlannerResult<Unit> {
        logger.i("Invoking purchase item usecase")

        val userId = authRepo.currentUserId

        if (userId == null) {
            logger.e("Invoking purchase item usecase requires user to be logged in")
            return PlannerResult.Error("User is not logged in")
        }

        val items = shopRepo.observeShopItems().first()

        val item =
            items.find { it.id == itemId }

        if (item == null) {
            logger.e("Item does not exist")
            return PlannerResult.Error("Item does not exist")
        }

        val boughtItems = userRepo.observeInventoryItems(userId).first().map { it.itemId }.toSet()

        if (boughtItems.contains(itemId)) {
            logger.e("Item has already been purchased")
            return PlannerResult.Error("Item has already been purchased")
        }

        userRepo.purchaseItem(userId, itemId, item.price)

        logger.i("Invoking purchase item usecase was successful")

        return PlannerResult.Success(Unit)
    }
}
