package marcel.uni.gamifiedplanner.domain.task.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.task.model.Task

interface TaskRepository {
    fun observeTasks(uid: String): Flow<List<Task>>
    suspend fun createTask(uid: String, task: Task)
    suspend fun updateTask(uid: String, task: Task)
    suspend fun deleteTask(uid: String, taskId: String)
}