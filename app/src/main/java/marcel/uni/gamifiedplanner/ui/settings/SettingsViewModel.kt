package marcel.uni.gamifiedplanner.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.auth.usecase.LogoutUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetNotificationStateUseCase

class SettingsViewModel(
    private val toggleDarkModeUseCase: SetDarkModeUseCase,
    private val toggleNotificationsUseCase: SetNotificationStateUseCase,
    private val getDarkModeUseCase: ObserveDarkModeUseCase,
    private val getNotificationsUseCase: ObserveNotificationStateUseCase,
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

