package marcel.uni.gamifiedplanner.data.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.suspendCancellableCoroutine
import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.util.PlannerResult
import kotlin.coroutines.resume

//
//  Implementation of FirebaseAuthRepository using Firebase Authentication.
//

class FirebaseAuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val logger: AppLogger,
) : FirebaseAuthRepository {
    override val currentUserId: String?
        get() = auth.currentUser?.uid

    override fun observerAuthState(): Flow<Boolean> =
        callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { users ->
                    trySend(users.currentUser != null)
                }
            auth.addAuthStateListener(listener)

            awaitClose {
                auth.removeAuthStateListener(listener)
            }
        }.catch { e ->
            logger.e("Error observing auth state: ${e.message}")
            emit(false)
        }

    override suspend fun login(
        email: String,
        password: String,
    ): String? {
        val result = auth.signInWithEmailAndPassword(email, password)

        if (!result.isSuccessful) {
            return null
        }

        return result.result.user?.uid
    }

    override suspend fun register(
        email: String,
        password: String,
    ): String? {
        val result = awaitAsResult<AuthResult>(auth.createUserWithEmailAndPassword(email, password))
        return result?.user?.uid
    }

    override fun logout() {
        auth.signOut()
    }

    private suspend fun <T> awaitAsResult(task: Task<T>): T? =
        suspendCancellableCoroutine { cont ->
            task.addOnCompleteListener { t ->
                if (t.isSuccessful) {
                    @Suppress("UNCHECKED_CAST")
                    cont.resume(t.result as T)
                } else {
                    cont.resume(
                        null,
                    )
                }
            }
            cont.invokeOnCancellation { }
        }
}
