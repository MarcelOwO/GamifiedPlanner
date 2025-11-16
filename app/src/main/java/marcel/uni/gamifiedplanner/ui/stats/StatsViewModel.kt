package marcel.uni.gamifiedplanner.ui.stats

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StatsViewModel: ViewModel() {
    private val _state = MutableStateFlow(StatsState())
    val state = _state.asStateFlow()
    fun load(){
    }
}

data class StatsState(
    val text:String = ""
)