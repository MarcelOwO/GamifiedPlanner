package marcel.uni.gamifiedplanner.domain.user.model

import com.google.firebase.Timestamp

data class UserInventoryItem(
    val id: String = "",
    val itemId: String = "",
    val acquiredAt: Timestamp = Timestamp.now(),
    val isEquipped: Boolean = false,
)
