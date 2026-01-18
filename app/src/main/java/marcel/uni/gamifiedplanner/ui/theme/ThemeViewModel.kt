package marcel.uni.gamifiedplanner.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.usecase.settings.ObserveDarkModeUseCase

class ThemeViewModel(
    private val observeDarkModeUseCase: ObserveDarkModeUseCase,
    private val logger: AppLogger
) : ViewModel() {

    val darkMode = observeDarkModeUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

}