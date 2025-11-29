package marcel.uni.gamifiedplanner.domain.user.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.model.UserData

interface UserRepository {
    fun getUserData(): Flow<UserData>
}