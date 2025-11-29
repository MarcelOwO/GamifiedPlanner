package marcel.uni.gamifiedplanner.data.shop.repository

import marcel.uni.gamifiedplanner.data.cloud.FirebaseFirestoreDataSource

class ShopRepositoryImpl(
    private val source : FirebaseFirestoreDataSource
) : ShopRepository {
}