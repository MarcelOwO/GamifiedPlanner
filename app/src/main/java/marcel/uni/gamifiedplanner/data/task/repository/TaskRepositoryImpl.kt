package marcel.uni.gamifiedplanner.data.task.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource
import marcel.uni.gamifiedplanner.data.task.dto.TaskDto
import marcel.uni.gamifiedplanner.data.task.dto.toDomain
import marcel.uni.gamifiedplanner.data.task.dto.toDto
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import java.util.UUID
import kotlin.collections.map

class TaskRepositoryImpl(
    private val auth: FirebaseAuthDataSource,
    private val firestore: FirebaseFirestore,
) : TaskRepository {

    private val uid: String
        get() = auth.currentUserId ?: error("User not logged in")
    private fun getTasksColl(uid:String) = firestore.document(uid).collection("tasks")

    override fun observeTasks(): Flow<List<Task>> = callbackFlow {
        val listener = getTasksColl(uid).addSnapshotListener { snapshot, error ->
            if( error !=null){
                close(error)
                return@addSnapshotListener
            }
            if(snapshot !=null){
                val taskDtos = snapshot.toObjects<TaskDto>()

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

        getTasksColl(uid)
            .document(id.toString())
            .set(dto)
            .await()
    }


    override suspend fun updateTask(
        task:Task,
    ) {
        getTasksColl(uid)
            .document(task.id)
            .set(task.toDto())
            .await()
    }

    override suspend fun deleteTask( taskId: String) {
        getTasksColl(uid)
            .document(taskId)
            .delete()
            .await()
    }


}