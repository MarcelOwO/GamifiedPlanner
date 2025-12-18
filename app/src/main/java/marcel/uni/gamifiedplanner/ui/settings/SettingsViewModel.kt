package marcel.uni.gamifiedplanner.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.auth.usecase.LogoutUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.GetDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.GetNotificationsUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ToggleDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ToggleNotificationsUseCase

class SettingsViewModel(
    private val toggleDarkModeUseCase: ToggleDarkModeUseCase,
    private val toggleNotificationsUseCase: ToggleNotificationsUseCase,
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val logoutUseCase: LogoutUseCase,
): ViewModel() {

    val notificationsEnabled:StateFlow<Boolean> = getNotificationsUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )
    val darkModeEnabled  = getDarkModeUseCase().stateIn(
        scope =viewModelScope,
        started =SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun toggleNotifications(enabled:Boolean){
        viewModelScope.launch {
            toggleNotificationsUseCase(enabled)
        }

    }

    fun toggleDarkMode(enabled:Boolean){
        viewModelScope.launch{
            toggleDarkModeUseCase(enabled)
        }
    }

    fun logout(){
        viewModelScope.launch{
            logoutUseCase()
        }
    }

}

