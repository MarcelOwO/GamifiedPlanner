package marcel.uni.gamifiedplanner.domain.achievement.model

import com.google.firebase.Timestamp


data class AchievementEvent(
    val totalTasksCompleted:Int,
    val currentStreak:Int,
    val taskCompletedAt: Timestamp = Timestamp.now()
)
