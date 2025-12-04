package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.user.model.UserStats

class GetUserDataUseCase(
    private val repo: UserRepository
){
    operator fun invoke(): Flow<UserStats?> = repo.observeUserStats()
}