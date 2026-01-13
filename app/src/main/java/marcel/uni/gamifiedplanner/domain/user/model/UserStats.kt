package marcel.uni.gamifiedplanner.domain.user.model

import com.google.firebase.Timestamp

data class UserStats(
    val xp: Long = 0,
    val currency: Long = 0,
    val streak: Long = 0,
    val lastLogin : Timestamp = Timestamp.now(),
)