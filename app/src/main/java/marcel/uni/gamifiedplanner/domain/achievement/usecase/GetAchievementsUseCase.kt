package marcel.uni.gamifiedplanner.domain.achievement.usecase

import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class GetAchievementsUseCase(
    private val achievementRepo: AchievementRepository,
    private val userRepo: UserRepository
) {
    operator suspend fun invoke():List<Achievement>{
        val achievements = achievementRepo.getAchievements()
        val completed = userRepo.getCompletedAchievements()

        var  all = achievements.map


        return emptyList()
    }
}