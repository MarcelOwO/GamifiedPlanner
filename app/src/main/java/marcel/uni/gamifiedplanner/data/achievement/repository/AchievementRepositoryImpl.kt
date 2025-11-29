package marcel.uni.gamifiedplanner.data.achievement.repository

import marcel.uni.gamifiedplanner.data.cloud.FirebaseFirestoreDataSource
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository

class AchievementRepositoryImpl(
    private val source: FirebaseFirestoreDataSource
): AchievementRepository {
}