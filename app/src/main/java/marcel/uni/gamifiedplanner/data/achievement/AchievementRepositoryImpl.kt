package marcel.uni.gamifiedplanner.data.achievement

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList

class AchievementRepositoryImpl(
    private val firestore: FirebaseFirestore,
) : AchievementRepository {
    private fun getCollection() = firestore.collection(firebaseConstants.ACHIEVEMENTS)

    override fun observeAchievements(uid: String): Flow<List<Achievement>> = getCollection().observeList<Achievement>()
}