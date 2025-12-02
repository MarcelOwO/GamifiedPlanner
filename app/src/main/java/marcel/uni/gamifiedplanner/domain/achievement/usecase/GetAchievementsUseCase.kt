package marcel.uni.gamifiedplanner.domain.achievement.usecase

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class GetAchievementsUseCase(
    private val achievementRepo: AchievementRepository,
    private val userRepo: UserRepository
) {
    operator suspend fun invoke(): List<Achievement> {
        val allAchievements = achievementRepo.getAchievements().first()
        val userProgress = userRepo.observeAchievementsProgress().first()

        val progressMap = userProgress.associateBy { it.achievementId }

        return allAchievements.map { achievement ->
            val progress = progressMap[achievement.id]

            if (progress != null) {
                achievement.copy(
                    achieved = true,
                    achievedAt = progress.unlockedAt
                )
            }
            else {
                achievement
            }
        }
    }
}