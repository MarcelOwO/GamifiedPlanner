package marcel.uni.gamifiedplanner.data.achievement.dto

import com.google.firebase.firestore.DocumentId

data class AchievementDto(
    @DocumentId    val id: String = "",
    val name: String = "",
    val description: String = "",
    val iconUrl: String = "",
)