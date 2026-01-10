package marcel.uni.gamifiedplanner.domain.achievement.model

import java.util.Date

data class AchievementDisplay(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val iconResId: Int? = null,
    val achieved : Boolean = false,
    val achievedAt : Date? = null,
)
