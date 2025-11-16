package marcel.uni.gamifiedplanner.ui.shop

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShopViewModel: ViewModel() {
    private val _state = MutableStateFlow(ShopState())
    val state = _state.asStateFlow()
    fun load(){
    }
}

data class ShopState(
    val text:String = ""
)