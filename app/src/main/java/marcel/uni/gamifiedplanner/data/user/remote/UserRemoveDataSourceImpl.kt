package marcel.uni.gamifiedplanner.data.user.remote

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import marcel.uni.gamifiedplanner.data.cloud.FirebaseFirestoreDataSource
import marcel.uni.gamifiedplanner.data.task.dto.TaskDto
import marcel.uni.gamifiedplanner.data.user.dto.UserDto

class UserRemoteDataSourceImpl(
    private val source : FirebaseFirestoreDataSource
): UserRemoteDataSource
{
    override fun userData(uid: String): Flow<UserDto> = callbackFlow {

        val listener = source.userData(uid).addSnapshotListener { snapshot, error ->
            if(error!= null){
                close(error)
                return@addSnapshotListener
            }

            val user =snapshot?.toObject(UserDto::class.java)

            if (user != null){
                trySend(user)
            }
        }
        awaitClose() {
            listener.remove()
        }
    }
}