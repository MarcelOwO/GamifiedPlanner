package marcel.uni.gamifiedplanner.data.user.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource
import marcel.uni.gamifiedplanner.data.user.dto.ToDomain
import marcel.uni.gamifiedplanner.data.user.dto.UserDto
import marcel.uni.gamifiedplanner.data.user.remote.UserRemoteDataSource
import marcel.uni.gamifiedplanner.domain.models.UserData

class UserRepositoryImpl(
    private val remote : UserRemoteDataSource,
    private val auth: FirebaseAuthDataSource
) : UserRepository {

   private val uid: String
        get() = auth.currentUserId ?: error("User not logged in")

    override fun getUserData(): Flow<UserData> {
        return  remote.userData(uid).map{user->user.ToDomain()}
    }

}