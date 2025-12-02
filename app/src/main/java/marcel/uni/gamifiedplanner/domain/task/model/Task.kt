package marcel.uni.gamifiedplanner.domain.task.model

data class Task(
    val id: String= "",
    val title: String="",
    val priority: Priority = Priority.MEDIUM,
    val status: TaskStatus = TaskStatus.OPEN,
    val description: String? = null,
    val duration: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)


