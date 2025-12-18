package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.user.model.UserInventoryItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class GetUserInventoryUseCase(
    private val userRepo: UserRepository
) {

    operator fun invoke(): Flow<List<UserInventoryItem>> =
       userRepo.observeInventory()
}