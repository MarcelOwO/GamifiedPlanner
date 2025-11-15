package marcel.uni.gamifiedplanner.domain.models

data class Achievement(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val achievedAt: Long = System.currentTimeMillis(),
    )