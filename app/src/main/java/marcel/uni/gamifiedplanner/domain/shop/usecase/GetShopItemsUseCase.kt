package marcel.uni.gamifiedplanner.domain.shop.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository

class GetShopItemsUseCase(
    private val repo: ShopRepository,
    private val logger: AppLogger
) {
    operator fun invoke(): Flow<List<ShopItem>> = repo.observeShopItems()
}
