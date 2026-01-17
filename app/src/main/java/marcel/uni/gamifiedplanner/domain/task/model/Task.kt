package marcel.uni.gamifiedplanner.domain.task.model

import com.google.firebase.Timestamp

data class Task(
    val id: String= "",
    val title: String="",
    val priority: Priority = Priority.MEDIUM,
    val status: TaskStatus = TaskStatus.OPEN,
    val startTime : Timestamp = Timestamp.now(),
    val description: String = "",
    val duration: Long = 0, // duration is in minutes
    val createdAt: Timestamp =Timestamp.now()
)


