package marcel.uni.gamifiedplanner.domain.user.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import java.util.Date


data class TaskHistoryItem(
    val id: String="",
    val taskId: String="",
    val taskTitle :String="",
    val taskPriority: Priority,
    val completedAt: Timestamp= Timestamp.now(),
)
