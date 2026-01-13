package marcel.uni.gamifiedplanner.domain.achievement.usecase

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.achievement.model.AchievementDisplay
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievementItem
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class GetAchievementsUseCase(
    private val achievementRepo: AchievementRepository,
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
    private val logger: AppLogger
) {
    suspend operator fun invoke(): PlannerResult<List<AchievementDisplay>> {
        logger.i("Getting achievements")

        val userId =
            authRepo.currentUserId ?: return PlannerResult.Error("User not logged in")

        val globalAchievements =
            achievementRepo
                .observeAchievements(userId)
                .first()

        val userAchievements = userRepo
            .observeAchievementItems(userId)
            .first()

        val userAchievementMap = userAchievements.associateBy { it.achievementId }

        val displayList = globalAchievements.map { achievement ->
            val userItem = userAchievementMap[achievement.id]
            val isUnlocked = userItem != null

            AchievementDisplay(
                id = achievement.id,
                name = achievement.name,
                description = achievement.description,
                iconResId = achievement.iconResId,
                achieved = isUnlocked,
                achievedAt = userItem?.unlockedAt,
            )
        }.sortedByDescending { it.achieved }

        return PlannerResult.Success(displayList)
    }
}
