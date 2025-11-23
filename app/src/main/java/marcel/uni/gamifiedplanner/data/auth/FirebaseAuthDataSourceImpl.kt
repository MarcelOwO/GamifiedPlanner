package marcel.uni.gamifiedplanner.data.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSourceImpl(
    private val auth: FirebaseAuth
) : FirebaseAuthDataSource {
    override val currentUserId: String?
        get() = auth.currentUser?.uid

    override fun observerAuthState(): Flow<Boolean> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { a ->
            trySend(a.currentUser != null)
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