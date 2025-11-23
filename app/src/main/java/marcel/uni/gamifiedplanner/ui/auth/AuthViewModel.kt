package marcel.uni.gamifiedplanner.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.usecases.auth.AuthStatusUseCase
import marcel.uni.gamifiedplanner.domain.usecases.auth.login.LogInUseCase
import marcel.uni.gamifiedplanner.domain.usecases.auth.LogoutUseCase
import marcel.uni.gamifiedplanner.domain.usecases.auth.login.LogInResult
import marcel.uni.gamifiedplanner.domain.usecases.auth.register.RegisterResult
import marcel.uni.gamifiedplanner.domain.usecases.auth.register.RegisterUseCase


class AuthViewModel(
    statusUseCase: AuthStatusUseCase,
    private val loginUseCase: LogInUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    val isLoggedIn: Flow<Boolean> = statusUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun login(email: String, password: String, onResult: (LogInResult) -> Unit) {
        viewModelScope.launch {
            loginUseCase(email, password)
                .also { result ->
                    onResult(result)
                }
        }
    }

    fun register(email: String, password: String, onResult: (RegisterResult) -> Unit) {
        viewModelScope.launch {
            registerUseCase(email, password).also { result ->
                onResult(result)
            }
        }
    }

    fun logout() {
        logoutUseCase()
    }
}

