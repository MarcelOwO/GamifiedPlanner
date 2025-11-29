package marcel.uni.gamifiedplanner.data.task.repository

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource
import marcel.uni.gamifiedplanner.data.cloud.FirebaseFirestoreDataSource
import marcel.uni.gamifiedplanner.data.task.dto.TaskDto
import marcel.uni.gamifiedplanner.data.task.dto.toDomain
import marcel.uni.gamifiedplanner.data.task.dto.toDto
import marcel.uni.gamifiedplanner.domain.task.model.Task
import java.util.UUID
import kotlin.collections.map

class TaskRepositoryImpl(
    private val auth: FirebaseAuthDataSource,
    private val source: FirebaseFirestoreDataSource,
) : TaskRepository {

    private val uid: String
        get() = auth.currentUserId ?: error("User not logged in")

    override fun observeTasks(): Flow<List<Task>> = callbackFlow {
        val ref = source.userTasks(uid)

        val listener = ref.addSnapshotListener { snapshot, error ->
            if( error !=null){
                close(error)
                return@addSnapshotListener
            }
            if(snapshot !=null){
                val taskDtos = snapshot?.toObjects(TaskDto::class.java)

                if( taskDtos == null){
                    return@addSnapshotListener
                }

                val tasks = taskDtos.map { it-> it.toDomain() }
                trySend(tasks)
            }

        }
        awaitClose() {
            listener.remove()
        }
    }

    override suspend fun createTask(
        task:Task,
    ) {
        val now = System.currentTimeMillis()
        val id = UUID.randomUUID()
        val taskWithMeta = task.copy(id = id.toString(), createdAt = now)
        val dto = taskWithMeta.toDto()

        source
            .userTasks(uid)
            .document(id.toString())
            .set(dto)
            .await()
    }


    override suspend fun updateTask(
        task:Task,
    ) {
        source
            .userTasks(uid)
            .document(task.id)
            .set(task.toDto())
            .await()
    }

    override suspend fun deleteTask( taskId: String) {
        source
            .userTasks(uid)
            .document(taskId)
            .delete()
            .await()
    }


}