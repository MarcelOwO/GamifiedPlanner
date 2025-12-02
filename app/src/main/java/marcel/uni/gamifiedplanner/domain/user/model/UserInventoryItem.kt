package marcel.uni.gamifiedplanner.domain.user.model

data class UserInventoryItem(
    val id: String,
    val itemId: String,
    val acquiredAt:Long,
    val isEquipped:Boolean,
)
