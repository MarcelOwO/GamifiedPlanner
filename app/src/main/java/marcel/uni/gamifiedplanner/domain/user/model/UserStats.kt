package marcel.uni.gamifiedplanner.domain.user.model

data class UserStats(
    val uid: String = "",
    val username: String = "",
    val xp: Int = 0,
    val currency: Int = 0,
)