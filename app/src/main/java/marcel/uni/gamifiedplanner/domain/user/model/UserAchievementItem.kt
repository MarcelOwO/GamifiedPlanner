package marcel.uni.gamifiedplanner.domain.user.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class UserAchievementItem(
    val uuid: String="",
    val achievementId: String="",
    val unlockedAt: Timestamp =Timestamp.now(),
)
