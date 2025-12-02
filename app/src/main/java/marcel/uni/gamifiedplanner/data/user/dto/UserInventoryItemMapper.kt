package marcel.uni.gamifiedplanner.data.user.dto

import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem

fun UserInventoryItem.toDto()=UserInventoryItemDto(
    id=this.id,
    itemId=this.itemId,
    acquiredAt=this.acquiredAt,
    isEquipped=this.isEquipped,
)
fun UserInventoryItemDto.toDomain()= UserInventoryItem(
    id=this.id,
    itemId=this.itemId,
    acquiredAt=this.acquiredAt,
    isEquipped=this.isEquipped,)
