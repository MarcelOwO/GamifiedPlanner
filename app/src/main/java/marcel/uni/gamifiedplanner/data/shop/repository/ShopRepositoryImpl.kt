package marcel.uni.gamifiedplanner.data.shop.repository

import marcel.uni.gamifiedplanner.data.cloud.FirebaseFirestoreDataSource
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository

class ShopRepositoryImpl(
    private val source : FirebaseFirestoreDataSource
) : ShopRepository {
}