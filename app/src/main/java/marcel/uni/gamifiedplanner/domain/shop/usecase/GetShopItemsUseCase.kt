package marcel.uni.gamifiedplanner.domain.shop.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.domain.task.model.Task

class GetShopItemsUseCase(
    private val repo: ShopRepository) {

    operator fun invoke(): Flow<List<ShopItem>> = repo.getShopItems()

}