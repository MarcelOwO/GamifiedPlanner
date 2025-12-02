package marcel.uni.gamifiedplanner.domain.shop.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem

interface ShopRepository {
    fun getShopItems(): Flow<List<ShopItem>>
}