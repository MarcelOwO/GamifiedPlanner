package marcel.uni.gamifiedplanner.data.task.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource
import marcel.uni.gamifiedplanner.data.task.dto.toDomain
import marcel.uni.gamifiedplanner.data.task.dto.toDto
import marcel.uni.gamifiedplanner.data.task.remote.TaskRemoteDataSource
import marcel.uni.gamifiedplanner.domain.models.Task
import kotlin.collections.map

class TaskRepositoryImpl(
    private val remote: TaskRemoteDataSource,
    private val auth: FirebaseAuthDataSource
) : TaskRepository {

    private val uid: String
        get() = auth.currentUserId ?: error("User not logged in")


    override fun observeTasks(): Flow<List<Task>> =
        remote.observeTasks(uid).map { list -> list.map { it.toDomain() } }


    override suspend fun createTask(task: Task) = remote.createTask(uid, task.toDto())

    override suspend fun updateTask(task: Task) =remote.updateTask(uid, task.toDto())

    override suspend fun deleteTask(task: Task) = remote.deleteTask(uid, task.id)

}