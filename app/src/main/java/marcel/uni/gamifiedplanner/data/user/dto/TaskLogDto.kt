package marcel.uni.gamifiedplanner.data.user.dto

data class TaskLogDto(
    val id: String,
    val taskId:String,
    val timestamp:Long,
    val priority:String,
)
