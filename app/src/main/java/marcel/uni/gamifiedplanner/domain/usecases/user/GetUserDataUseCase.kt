package marcel.uni.gamifiedplanner.domain.usecases.user

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.data.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.models.UserData

class GetUserDataUseCase(
    private val repo: UserRepository
){
    operator fun invoke(): Flow<UserData> = repo.getUserData()
}