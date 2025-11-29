package marcel.uni.gamifiedplanner.data.user.remote

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.data.user.dto.UserDto

interface UserRemoteDataSource
{
    fun userData(uid:String): Flow<UserDto>
}