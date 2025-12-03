package marcel.uni.gamifiedplanner.domain.achievement.event

import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.events.DomainEvent
import marcel.uni.gamifiedplanner.domain.events.DomainEventHandler
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository

class AchievementEventHandler(
    private val achievementRepo: AchievementRepository,
    private val userRepo: UserRepository,
) : DomainEventHandler {
    override val order = 0
    override suspend fun handle(event: DomainEvent) {
        val achievements = achievementRepo.getAchievements();
        val user = userRepo.observeAchievementsProgress();

        when (event) {
            is DomainEvent.ItemPurchasedEvent -> {

            }
            is DomainEvent.TaskCompletedEvent -> {

            }
            else -> {
            }
        }





    }
}