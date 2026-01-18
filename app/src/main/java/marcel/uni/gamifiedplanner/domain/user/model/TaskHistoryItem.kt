package marcel.uni.gamifiedplanner.domain.user.model

import com.google.firebase.Timestamp
import marcel.uni.gamifiedplanner.domain.task.model.Priority


data class TaskHistoryItem(
    val id: String = "",
    val taskId: String = "",
    val taskTitle: String = "",
    val taskPriority: Priority = Priority.LOW,
    val completedAt: Timestamp = Timestamp.now(),
)
