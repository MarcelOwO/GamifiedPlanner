package marcel.uni.gamifiedplanner.data.shop.repository

import androidx.compose.runtime.snapshotFlow
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import marcel.uni.gamifiedplanner.data.shop.dto.ShopItemDto
import marcel.uni.gamifiedplanner.data.shop.dto.ToDomain
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList

class ShopRepositoryImpl(
    private val firestore: FirebaseFirestore,
) : ShopRepository {
    private fun getCollection() = firestore.collection(firebaseConstants.SHOPITEMS)

    override fun getShopItems(): Flow<List<ShopItem>> = getCollection().observeList<ShopItem>()
}
