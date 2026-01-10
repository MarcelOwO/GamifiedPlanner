package marcel.uni.gamifiedplanner.domain.auth.repository

import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    val currentUserId: String?
    fun observerAuthState(): Flow<Boolean>
    suspend fun login(email: String, password: String)
    suspend fun register(email: String, password: String): String?
    fun logout()
}