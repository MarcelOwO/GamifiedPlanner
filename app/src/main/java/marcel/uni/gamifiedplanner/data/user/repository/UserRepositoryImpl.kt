package marcel.uni.gamifiedplanner.data.user.repository

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource
import marcel.uni.gamifiedplanner.data.cloud.FirebaseFirestoreDataSource
import marcel.uni.gamifiedplanner.data.user.dto.ToDomain
import marcel.uni.gamifiedplanner.data.user.dto.UserDto
import marcel.uni.gamifiedplanner.domain.user.model.UserData
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class UserRepositoryImpl(
    private val auth: FirebaseAuthDataSource,
    private val source : FirebaseFirestoreDataSource,
) : UserRepository {

   private val uid: String
        get() = auth.currentUserId ?: error("User not logged in")

    override fun getUserData(): Flow<UserData> = callbackFlow {
        val listener = source.userData(uid).addSnapshotListener { snapshot, error ->
            if(error!= null){
                close(error)
                return@addSnapshotListener
            }

            if(snapshot != null){
                val userDtos = snapshot.toObject(UserDto::class.java)

                if (userDtos == null){
                    return@addSnapshotListener
                }
                val user = userDtos.ToDomain()

                trySend(user)
            }
        }
        awaitClose() {
            listener.remove()
        }
    }

}