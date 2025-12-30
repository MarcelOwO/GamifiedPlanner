package marcel.uni.gamifiedplanner.data.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository

class FirebaseAuthRepositoryImpl(
    private val auth: FirebaseAuth
) : FirebaseAuthRepository {

    override val currentUserId: String?
        get() = auth.currentUser?.uid

    override fun observerAuthState(): Flow<Boolean> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { users ->
            trySend(users.currentUser != null)
        }
        auth.addAuthStateListener(listener)

        awaitClose {
            auth.removeAuthStateListener(listener)
        }
    }

    override suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)

    }

    override suspend fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    override fun logout() {
        auth.signOut()
    }


}