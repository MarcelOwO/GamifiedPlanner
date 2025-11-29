package marcel.uni.gamifiedplanner.data.user.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.data.user.dto.UserDto
import marcel.uni.gamifiedplanner.domain.models.UserData

interface UserRepository {
    fun getUserData(): Flow<UserData>
}