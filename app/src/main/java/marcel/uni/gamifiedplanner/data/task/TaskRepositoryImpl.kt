package marcel.uni.gamifiedplanner.data.task

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList
import java.util.UUID

class TaskRepositoryImpl(
    private val auth: FirebaseAuthRepository,
    private val firestore: FirebaseFirestore,
) : TaskRepository {
    private fun getUserDoc(uid: String) = firestore
        .collection(firebaseConstants.USERS)
        .document(uid)

    private fun getTasksColl(uid: String) =
        getUserDoc(uid)
            .collection(firebaseConstants.TASKS)

    override fun observeTasks(uid: String): Flow<List<Task>> =
        getTasksColl(uid).observeList<Task>()

    override suspend fun createTask(
        uid: String,
        task: Task,
    ) {
        runCatching{
           val docRef = getTasksColl(uid).document()
           val taskWithMeta =  task.copy(
               id = docRef.id,
               createdAt = Timestamp.now()
           )
           docRef.set(taskWithMeta).await()
        }.onFailure{
        }
    }

    override suspend fun updateTask(
        uid: String,
        task: Task,
    ) {
        if (task.id.isBlank()) return

        getTasksColl(uid)
            .document(task.id)
            .set(task)
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
}