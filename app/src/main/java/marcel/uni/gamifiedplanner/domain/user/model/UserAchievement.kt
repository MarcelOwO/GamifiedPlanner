package marcel.uni.gamifiedplanner.domain.user.model

data class UserAchievement(
    val uuid : String,
    val achievementId: String,
    val unlockedAt: Long,
);