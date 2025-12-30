package marcel.uni.gamifiedplanner.data.user.dto.mapper

import marcel.uni.gamifiedplanner.data.user.dto.UserInventoryItemDto
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem

fun UserInventoryItemDto.toDomain(): UserInventoryItem {
    return UserInventoryItem(
        id =this.id,
        itemId = this.itemId,
        acquiredAt = this.acquiredAt,
        isEquipped = this.isEquipped,
    )
}