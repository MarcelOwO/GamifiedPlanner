package marcel.uni.gamifiedplanner.domain.user.model

import com.google.firebase.Timestamp

data class UserAchievementItem(
    val uuid: String = "",
    val achievementId: String = "",
    val unlockedAt: Timestamp = Timestamp.now(),
)
