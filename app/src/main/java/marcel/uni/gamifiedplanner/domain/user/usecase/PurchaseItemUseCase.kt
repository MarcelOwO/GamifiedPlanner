package marcel.uni.gamifiedplanner.domain.user.usecase

import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class PurchaseItemUseCase(
    private val userRepo: UserRepository,
    private val shopRepo: ShopRepository,
    ) {
}