package marcel.uni.gamifiedplanner.domain.achievement.repository

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement

interface AchievementRepository {
    fun observeAchievements(uid: String): Flow<List<Achievement>>
}
