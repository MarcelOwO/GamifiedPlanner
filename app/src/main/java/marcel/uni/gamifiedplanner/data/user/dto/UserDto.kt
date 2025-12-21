package marcel.uni.gamifiedplanner.data.user.dto

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDto (
    @DocumentId val uid: String = "",
    var username: String = "",
    var xp: Long = 0,
    var currency: Long = 0,
    var settings : UserSettingsDto = UserSettingsDto(),
    var purchasedItems: Map<String,Boolean> = emptyMap(),
    var completedAchievements : Map<String,Long> = emptyMap(),
    var taskLog : Map<String,TaskLogDto> = emptyMap(),
)