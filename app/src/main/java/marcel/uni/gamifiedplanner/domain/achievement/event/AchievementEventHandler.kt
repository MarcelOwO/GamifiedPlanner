package marcel.uni.gamifiedplanner.domain.achievement.event

import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.events.DomainEvent
import marcel.uni.gamifiedplanner.domain.events.DomainEventHandler
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.util.PlannerResult

class AchievementEventHandler(
    private val achievementRepo: AchievementRepository,
    private val userRepo: UserRepository,
    private val authRepo: FirebaseAuthRepository,
) : DomainEventHandler {
    override val order = 0

    override suspend fun handle(event: DomainEvent): PlannerResult<Nothing> {
        val userId = authRepo.currentUserId

        if (userId == null) {
            return PlannerResult.ValidationError("User is not logged in")
        }
        val achievements = achievementRepo.getAchievements(userId)

        val user = userRepo.observeAchievementsProgress(userId)

        when (event) {
            is DomainEvent.ItemPurchasedEvent -> {
            }

            is DomainEvent.TaskCompletedEvent -> {
            }

            else -> {
            }
        }
        return PlannerResult.Success<Nothing>(null)
    }
}
