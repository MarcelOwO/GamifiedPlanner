package marcel.uni.gamifiedplanner.ui.header


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppHeaderViewModel : ViewModel() {
    private val _state = MutableStateFlow(AppHeaderState())
    val state: StateFlow<AppHeaderState> = _state
    fun load() {
    }
}

data class AppHeaderState(
    val text: String = ""
)
