package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.model.UserProfile

class ObserveUserUsernameUseCase(
    private val userRepo: UserRepository
    private val authRepo: FirebaseAuthRepository
) {

    operator fun invoke(): Flow<String> {
        val userId = authRepo.currentUserId

        if( userId == null ){
            return flowOf("")
        }
        return userRepo.observeProfile(userId).map{profile->
            profile.username
        }
    }
}
