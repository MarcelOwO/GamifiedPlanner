package marcel.uni.gamifiedplanner.domain.shop.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository

class GetShopItemsUseCase(
    private val repo: ShopRepository) {
    operator fun invoke(): Flow<List<ShopItem>> = repo.getShopItems()
}