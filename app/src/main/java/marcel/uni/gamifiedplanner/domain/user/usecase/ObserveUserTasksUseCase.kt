package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.flow.flowOf
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository

class ObserveUserTaskUseCase(
    private val userRepo: UserRepository
    private val authRepo: FirebaseAuthRepository
) {

    operator fun invoke(): Flow<List<TaskHistoryItem>> {
        val userId = authRepo.currentUserId

        if( userId == null ){
            return flowOf(emptyList())
        }
        return userRepo.observeInventory(userId).map{inventory->
            inventory.tasks
        }
    }
}
