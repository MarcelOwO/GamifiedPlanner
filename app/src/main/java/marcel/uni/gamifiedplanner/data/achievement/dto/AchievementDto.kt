package marcel.uni.gamifiedplanner.data.achievement.dto

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class AchievementDto(
    @DocumentId val id: String = "",
    val name: String = "",
    val description: String = "",
    val iconResId: Int? = null,
)
