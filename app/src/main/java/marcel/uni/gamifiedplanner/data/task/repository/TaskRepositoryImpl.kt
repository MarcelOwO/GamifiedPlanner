package marcel.uni.gamifiedplanner.data.task.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.data.task.dto.TaskDto
import marcel.uni.gamifiedplanner.data.task.dto.toDomain
import marcel.uni.gamifiedplanner.data.task.dto.toDto
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import java.util.UUID
import kotlin.collections.map
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList

class TaskRepositoryImpl(
    private val auth: FirebaseAuthRepository,
    private val firestore: FirebaseFirestore,
) : TaskRepository {

    private fun getUserDoc(uid: String) = firestore.collection(firebaseConstants.USERS).document(uid)

    private fun getTasksColl(uid: String) =
        getUserDoc(uid)
        .collection(firebaseConstants.TASKS)

    override fun observeTasks(uid: String): Flow<List<Task>> =getTasksColl(uid).observeList<Task>()


    override suspend fun createTask(
        uid: String,
        task: Task,
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
        uid: String,
        task: Task,
    ) {

        getTasksColl(uid)
            .document(task.id)
            .set(task.toDto())
            .await()
    }

    override suspend fun deleteTask(
        uid: String,
        taskId: String,
    ) {

        getTasksColl(uid)
            .document(taskId)
            .delete()
            .await()
    }

