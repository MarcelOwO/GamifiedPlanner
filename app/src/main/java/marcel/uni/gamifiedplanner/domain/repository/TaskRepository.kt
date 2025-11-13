package marcel.uni.gamifiedplanner.domain.repository


import marcel.uni.gamifiedplanner.domain.model.Task

interface TaskRepository {
    suspend fun getTasks(userId:String): List<Task>
    suspend fun createTask(task: Task)
    suspend fun updateTask(task:Task)
    suspend fun deleteTask(taskId:String)
}