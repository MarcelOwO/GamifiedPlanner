package marcel.uni.gamifiedplanner.data.shop

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList

class ShopRepositoryImpl(
    private val firestore: FirebaseFirestore,
) : ShopRepository {
    private fun getCollection() = firestore.collection(firebaseConstants.SHOP_ITEMS)

    override fun observeShopItems(): Flow<List<ShopItem>> = getCollection().observeList<ShopItem>()
}