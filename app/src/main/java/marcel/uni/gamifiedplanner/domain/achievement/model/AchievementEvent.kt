package marcel.uni.gamifiedplanner.domain.achievement.model

import java.time.LocalTime

data class AchievementEvent(
    val totalTasksCompleted:Int,
    val currentStreak:Int,
    val taskCompletedAt: LocalTime? = null
)
