package marcel.uni.gamifiedplanner.domain.task.model

import com.google.firebase.Timestamp

data class Task(
    val id: String= "",
    val title: String="",
    val priority: Priority = Priority.MEDIUM,
    val status: TaskStatus = TaskStatus.OPEN,
    val startTime : Timestamp? = null,
    val description: String? = null,
    val duration: Long? = null,
    val createdAt: Timestamp =Timestamp.now()
)


