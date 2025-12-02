package marcel.uni.gamifiedplanner.data.achievement.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import marcel.uni.gamifiedplanner.data.achievement.dto.AchievementDto
import marcel.uni.gamifiedplanner.data.achievement.dto.ToDomain
import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository

class AchievementRepositoryImpl(
    private val firestore: FirebaseFirestore
) : AchievementRepository {
    private fun getCollection() = firestore.collection("achievements")

    override fun getAchievements(): Flow<List<Achievement>> = callbackFlow {

        getCollection().addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val dtos = snapshot.toObjects<AchievementDto>()
                val achievements = dtos.map { it.ToDomain() }
                trySend(achievements)
            }
        }
    }
}