package marcel.uni.gamifiedplanner.domain.user.model

data class UserAchievementItem(
    val uuid: String,
    val achievementId: String,
    val unlockedAt: Long,
)
