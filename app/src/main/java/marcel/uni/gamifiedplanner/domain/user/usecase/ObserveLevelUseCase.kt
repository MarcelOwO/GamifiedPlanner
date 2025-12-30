package marcel.uni.gamifiedplanner.domain.user.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.distinctUntilChanged
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import kotlin.math.*

class ObserveLevelUseCase(
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
) {
    operator fun invoke(): Flow<Int> {
        val userId = authRepo.currentUserId

        if (userId == null) {
            return flowOf(0)
        }

        return userRepo
            .observeStats(userId)
            .map { stats ->
                CalculateLevel(stats.xp)
            }.distinctUntilChanged()
    }

    private fun CalculateLevel(xp:Long):Int{
        if (xp < 100) return 1

        val level =log(xp.toDouble() / 100 , 2.0)

        return floor(level).toInt()+1




    }


}

