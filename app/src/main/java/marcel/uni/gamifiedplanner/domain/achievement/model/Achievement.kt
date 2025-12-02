package marcel.uni.gamifiedplanner.domain.achievement.model

data class Achievement(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val iconUrl: String = "",
    val achieved : Boolean = false,
    val achievedAt : Long? = null,
    )
