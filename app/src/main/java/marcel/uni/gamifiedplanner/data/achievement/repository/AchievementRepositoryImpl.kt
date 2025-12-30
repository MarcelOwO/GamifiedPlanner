package marcel.uni.gamifiedplanner.data.achievement.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import marcel.uni.gamifiedplanner.data.achievement.dto.AchievementDto
import marcel.uni.gamifiedplanner.data.achievement.dto.ToDomain
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.util.PlannerResult
import marcel.uni.gamifiedplanner.util.firebaseConstants
import marcel.uni.gamifiedplanner.util.observeList

class AchievementRepositoryImpl(
    private val firestore: FirebaseFirestore,
) : AchievementRepository {
    private fun getCollection() = firestore.collection(firebaseConstants.ACHIEVEMENTS)

    override fun observeAchievements(uid: String): Flow<List<Achievement>> = getCollection().observeList<Achievement>()
}
