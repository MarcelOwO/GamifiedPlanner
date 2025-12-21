package marcel.uni.gamifiedplanner.data.task.dto

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus
import kotlin.time.Duration

@IgnoreExtraProperties
data class TaskDto(
    @DocumentId val id: String = "",
    val title: String = "",
    val description: String? = null,
    val duration: Long? = null,
    val startTime: Long? = null,
    val priority: Priority = Priority.MEDIUM,
    val status: TaskStatus = TaskStatus.OPEN,
    val createdAt: Long = 0L,
)