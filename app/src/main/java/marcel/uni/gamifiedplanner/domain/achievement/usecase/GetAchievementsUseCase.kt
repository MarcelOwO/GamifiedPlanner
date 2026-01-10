package marcel.uni.gamifiedplanner.domain.achievement.usecase

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class GetAchievementsUseCase(
    private val achievementRepo: AchievementRepository,
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
) {
    suspend operator fun invoke(): PlannerResult<List<Achievement>> {
        val userId =
            authRepo.currentUserId ?: return PlannerResult.ValidationError("User not logged in")

        //return PlannerResult.Success(achievementRepo.observeAchievements(userId).first())

        val allAchievements =
            achievementRepo
                .observeAchievements(userId)
                .first()

        val userProgress = userRepo.observeInventory(userId).first().achievements

        val progressMap = userProgress.associateBy { it.achievementId }

        val updatedAchievements = allAchievements.map { it }

        val map =
            allAchievements.map { achievement ->
                val progress = progressMap[achievement.id]

                if (progress != null) {
                    achievement.copy(
                        achieved = true,
                        achievedAt = progress.unlockedAt,
                    )
                } else {
                    achievement
                }
            }
        return PlannerResult.Success(map)
    }
}
