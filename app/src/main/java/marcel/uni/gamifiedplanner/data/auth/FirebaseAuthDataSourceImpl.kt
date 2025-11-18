package marcel.uni.gamifiedplanner.data.auth

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthDAtaSourceImpl(
    private val auth: FirebaseAuth
): FirebaseAuthDataSource
{
    override val currentUserId: String?
        get() = auth.currentUser?.uid


}