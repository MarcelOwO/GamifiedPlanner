package marcel.uni.gamifiedplanner.data.shop.dto

import com.google.firebase.firestore.DocumentId

data class ShopItemDto(
    @DocumentId val id:String="",
    val name:String="",
    val description:String="",
    val price:Int=0,
    val iconUrl:String="",
)