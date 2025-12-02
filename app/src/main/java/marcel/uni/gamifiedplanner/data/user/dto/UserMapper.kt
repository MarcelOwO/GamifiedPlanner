package marcel.uni.gamifiedplanner.data.user.dto

import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import kotlin.String


fun UserDto.ToDomain() =
    UserStats(
        uid = this.uid,
        username = this.username,
        xp = this.xp,
        currency = this.currency,
    );

fun UserStats.ToDto() =
    UserDto(
        uid = this.uid,
        username = this.username,
        xp = this.xp,
        currency = this.currency,
    );