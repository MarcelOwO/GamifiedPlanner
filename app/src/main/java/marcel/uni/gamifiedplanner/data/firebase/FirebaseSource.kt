package marcel.uni.gamifiedplanner.data.firebase

import com.google.firebase.auth.FirebaseAuth

class FirebaseSource(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {
}