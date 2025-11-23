package marcel.uni.gamifiedplanner.data.auth

import kotlinx.coroutines.flow.Flow

interface FirebaseAuthDataSource {
    val currentUserId:String?
    fun observerAuthState(): Flow<Boolean>
    suspend fun login(email:String,password:String)
    suspend fun register(email:String,password:String)
    fun logout()
}