package marcel.uni.gamifiedplanner.data.shop.dto

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class ShopItemDto(
    @DocumentId val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Int = 0,
    val iconResId: Int? = null,
)