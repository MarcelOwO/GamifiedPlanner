package marcel.uni.gamifiedplanner.data.task

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList
import java.util.UUID

class TaskRepositoryImpl(
    private val auth: FirebaseAuthRepository,
    private val firestore: FirebaseFirestore,
    private val logger: AppLogger,
) : TaskRepository {
    private fun getUserDoc(uid: String) =
        firestore
            .collection(firebaseConstants.USERS)
            .document(uid)

    private fun getTasksColl(uid: String) =
        getUserDoc(uid)
            .collection(firebaseConstants.TASKS)

    override fun observeTasks(uid: String): Flow<List<Task>> =
        getTasksColl(uid).observeList<Task>().catch { e ->
            logger.e("Error observing tasks: ${e.message}")
            emit(emptyList())
        }

    override suspend fun createTask(
        uid: String,
        task: Task,
    ) {
        runCatching {
            val docRef = getTasksColl(uid).document()
            val taskWithMeta =
                task.copy(
                    id = docRef.id,
                    createdAt = Timestamp.now(),
                )
            docRef.set(taskWithMeta).await()
        }.onFailure { e ->
            logger.e("Error creating task: ${e.message}")
        }
    }

    override suspend fun updateTask(
        uid: String,
        task: Task,
    ) {
        if (task.id.isBlank()) {
            logger.e("Error updating task: Task ID is blank")
            return
        }
        runCatching {
            getTasksColl(uid)
                .document(task.id)
                .set(task)
                .await()
        }.onFailure { e ->
            logger.e("Error updating task: ${e.message}")
        }
    }

    override suspend fun deleteTask(
        uid: String,
        taskId: String,
    ) {
        runCatching {
            getTasksColl(uid)
                .document(taskId)
                .delete()
                .await()
        }.onFailure { e ->
            logger.e("Error deleting task: ${e.message}")
        }
    }
}
