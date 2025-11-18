package marcel.uni.gamifiedplanner.data.task.remote

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.data.task.dto.TaskDto

interface TaskRemoteDataSource {
    fun observeTasks(uid:String): Flow<List<TaskDto>>
    suspend fun createTask(userId: String, taskDto: TaskDto)
    suspend fun updateTask(userId: String, taskDto: TaskDto)
    suspend fun deleteTask(userId: String, taskId: String)
}