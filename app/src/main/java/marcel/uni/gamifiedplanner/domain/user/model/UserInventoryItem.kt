package marcel.uni.gamifiedplanner.domain.user.model

import com.google.firebase.Timestamp
import java.util.Date
import com.google.firebase.firestore.ServerTimestamp

data class UserInventoryItem(
    val id: String="",
    val itemId: String="",
    val acquiredAt: Timestamp = Timestamp.now(),
    val isEquipped:Boolean= false,
)
