package marcel.uni.gamifiedplanner.domain.user.model

data class User(
    val uid :String,
    val profile: UserProfile,
    val stats: UserStats,
    val inventory: UserInventory,
    val settings: UserSettings,
)
