package marcel.uni.gamifiedplanner.data.achievement

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList

class AchievementRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val logger: AppLogger,
) : AchievementRepository {
    private fun getCollection() = firestore.collection(firebaseConstants.ACHIEVEMENTS)

    override fun observeAchievements(uid: String): Flow<List<Achievement>> =
        getCollection().observeList<Achievement>().catch { e ->
            logger.e("Error observing achievements: ${e.message}")
            emit(emptyList())
        }
}
