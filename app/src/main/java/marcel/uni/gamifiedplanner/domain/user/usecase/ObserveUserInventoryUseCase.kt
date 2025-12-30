package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository

class ObserveUserInventoryUseCase(
    private val userRepo: UserRepository
    private val authRepo: FirebaseAuthRepository
) {

    operator fun invoke(): Flow<List<UserInventoryItem>> {
        val userId = authRepo.currentUserId

        if( userId == null ){
            return flowOf(emptyList())
        }
        return userRepo.observeInventory(userId).map{inventory->
            inventory.items
        }
    }
}
