package marcel.uni.gamifiedplanner.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel: ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()
    fun load(){
    }
}

data class SettingsState(
    val text:String = ""
)