package marcel.uni.gamifiedplanner.domain.user.model

data class User(
    val uid :String="",
    val profile: UserProfile = UserProfile(),
    val stats: UserStats= UserStats(),
    val settings: UserSettings=UserSettings(),
)
