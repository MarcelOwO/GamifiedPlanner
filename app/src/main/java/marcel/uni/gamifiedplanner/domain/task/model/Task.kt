package marcel.uni.gamifiedplanner.domain.task.model

data class Task(
    val id: String = "",
    val title: String="",
    val description: String? =null,
    val priority: Priority = Priority.MEDIUM,
    val status: TaskStatus= TaskStatus.OPEN,
    val createdAt: Long = System.currentTimeMillis()
)


