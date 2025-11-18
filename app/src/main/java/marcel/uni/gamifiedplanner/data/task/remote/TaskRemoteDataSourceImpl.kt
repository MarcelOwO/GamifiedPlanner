package marcel.uni.gamifiedplanner.data.task.remote

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.data.cloud.FirebaseFirestoreDataSource
import marcel.uni.gamifiedplanner.data.task.dto.TaskDto

class TaskRemoteDataSourceImpl(
    private val source: FirebaseFirestoreDataSource
) : TaskRemoteDataSource {
    override fun observeTasks(uid: String): Flow<List<TaskDto>> = callbackFlow {
        val ref = source.userTasks(uid)
        val listener = ref.addSnapshotListener { snapshot, _ ->
            trySend(snapshot?.toObjects(TaskDto::class.java).orEmpty())
        }
        awaitClose() {
            listener.remove()
        }
    }

    override suspend fun createTask(
        userId: String,
        taskDto: TaskDto
    ) {
        source
            .userTasks(userId)
            .document(taskDto.id)
            .set(taskDto)
            .await()
    }

    override suspend fun updateTask(
        userId: String,
        taskDto: TaskDto
    ) {
        source
            .userTasks(userId)
            .document(taskDto.id)
            .set(taskDto)
            .await()
    }

    override suspend fun deleteTask(userId: String, taskId: String) {
        source
            .userTasks(userId)
            .document(taskId)
            .delete()
            .await()
    }
}