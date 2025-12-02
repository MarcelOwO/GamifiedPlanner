package marcel.uni.gamifiedplanner.data.shop.dto

import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem


fun ShopItemDto.ToDomain()=
    ShopItem(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
    )

fun ShopItem.ToDto()=
    ShopItemDto(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
    )
