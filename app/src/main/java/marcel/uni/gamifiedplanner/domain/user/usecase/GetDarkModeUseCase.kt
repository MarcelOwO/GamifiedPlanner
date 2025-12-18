package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class GetDarkModeUseCase(
    private val userRepo: UserRepository
) {
     operator fun invoke(): Flow<Boolean>
    = userRepo.observeDarkMode()
}