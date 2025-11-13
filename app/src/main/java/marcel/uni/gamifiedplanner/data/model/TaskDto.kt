package marcel.uni.gamifiedplanner.data.model

data class TaskDto(

    val id: String? = null,
    val title: String = "",
    val description: String? = "",
    val priority: String = "MEDIUM",
    val status: String = "OPEN",
    val createdAt: Long = System.currentTimeMillis(),
)