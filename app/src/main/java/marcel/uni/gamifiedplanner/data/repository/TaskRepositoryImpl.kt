package marcel.uni.gamifiedplanner.data.repository

import marcel.uni.gamifiedplanner.data.firebase.FirebaseSource
import marcel.uni.gamifiedplanner.domain.model.Task
import marcel.uni.gamifiedplanner.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : TaskRepository {
    private val source = FirebaseSource(db, auth)

    override suspend fun getTasks(userId: String) =
        source.getTasks().map { it.toDomain() }

    override suspend fun createTask(task: Task) =
        source.addTask(task.toDto())

    override suspend fun updateTask(task: Task) =
        source.updateTask(task.toDto())

    override suspend fun deleteTask(taskId: String) =
        source.deleteTask(taskId)
}
