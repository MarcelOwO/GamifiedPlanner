package marcel.uni.gamifiedplanner.domain.user.usecase

import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class SetDarkModeUseCase(
    private val userRepo: UserRepository
) {
    suspend operator fun invoke(enabled:Boolean)=
        userRepo.toggleDarkMode(enabled)
}