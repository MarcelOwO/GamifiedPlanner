package marcel.uni.gamifiedplanner.ui.header


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import marcel.uni.gamifiedplanner.domain.models.UserData
import marcel.uni.gamifiedplanner.domain.usecases.user.GetUserDataUseCase

class AppHeaderViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    ) : ViewModel() {

    private val userData: StateFlow<UserData?> = getUserDataUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val currentLevel : StateFlow<Int?> = userData
        .map{   it?.level }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
           initialValue = null
        )

    val currentXp : StateFlow<Int?> = userData
        .map{   it?.xp  }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
        )

    val xpProgress : StateFlow<Float> = userData
        .map{   (it?.xp ?: 0) / 100f  }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0f
        )

}



