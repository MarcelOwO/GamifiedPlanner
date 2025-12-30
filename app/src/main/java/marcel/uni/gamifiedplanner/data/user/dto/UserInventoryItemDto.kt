package marcel.uni.gamifiedplanner.data.user.dto

data class UserInventoryItemDto(
    val id: String,
    val itemId: String,
    val acquiredAt:Long,
    val isEquipped:Boolean,
)
