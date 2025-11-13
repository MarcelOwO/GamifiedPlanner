package marcel.uni.gamifiedplanner.domain.usecases

import marcel.uni.gamifiedplanner.data.repository.TaskRepositoryImpl

class TaskUseCases(private val repo: TaskRepositoryImpl) {
    suspend fun getAll(userId: String) = repo.getTasks(userId)
    suspend fun add(task:Task) = repo.createTask(task)
    suspend fun update(task:Task) =repo.updateTask(task)
    suspend fun delete(taskId:String) = repo.deleteTask(taskId)

}