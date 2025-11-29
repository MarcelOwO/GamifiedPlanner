package marcel.uni.gamifiedplanner.data.achievement.repository

import marcel.uni.gamifiedplanner.data.cloud.FirebaseFirestoreDataSource

class AchievementRepositoryImpl(
    private val source: FirebaseFirestoreDataSource
): AchievementRepository {
}