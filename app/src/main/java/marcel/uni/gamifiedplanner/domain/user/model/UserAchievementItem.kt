package marcel.uni.gamifiedplanner.domain.user.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class UserAchievementItem(
    val uuid: String="",
    val achievementId: String="",
    @ServerTimestamp
    val unlockedAt: Date?=null,
)
