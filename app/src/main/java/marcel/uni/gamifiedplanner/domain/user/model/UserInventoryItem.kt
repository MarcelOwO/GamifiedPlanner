package marcel.uni.gamifiedplanner.domain.user.model

import java.util.Date
import com.google.firebase.firestore.ServerTimestamp

data class UserInventoryItem(
    val id: String="",
    val itemId: String="",
    @ServerTimestamp
    val acquiredAt: Date? = null,
    val isEquipped:Boolean= false,
)
