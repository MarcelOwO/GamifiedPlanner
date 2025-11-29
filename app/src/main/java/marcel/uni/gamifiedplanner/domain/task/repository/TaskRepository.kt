package marcel.uni.gamifiedplanner.domain.task.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.task.model.Task

interface TaskRepository {
    fun observeTasks(): Flow<List<Task>>
    suspend fun createTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(taskId: String)
}