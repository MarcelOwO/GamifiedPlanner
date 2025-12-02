package marcel.uni.gamifiedplanner.domain.user.model

data class UserStats(
    val uid: String = "",
    val username: String = "",
    val xp: Long = 0,
    val currency: Long = 0,
)