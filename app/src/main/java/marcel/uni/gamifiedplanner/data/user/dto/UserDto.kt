package marcel.uni.gamifiedplanner.data.user.dto

import marcel.uni.gamifiedplanner.domain.task.model.Task

data class UserDto (
    val uid: String = "",
    val username: String = "",
    val xp: Long = 0,
    val currency: Long = 0,
)