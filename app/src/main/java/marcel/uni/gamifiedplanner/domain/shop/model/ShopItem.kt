package marcel.uni.gamifiedplanner.domain.shop.model

data class ShopItem(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Long = 0,
    val iconResId: Int? = null,
)
