package marcel.uni.gamifiedplanner.ui.header


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import marcel.uni.gamifiedplanner.domain.services.ProgressionService
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.domain.user.usecase.GetUserDataUseCase

class AppHeaderViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val progressionService: ProgressionService
    ) : ViewModel() {

    private val userData: StateFlow<UserStats?> = getUserDataUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val currentLevel : StateFlow<Int?> = userData
        .map{ it->
           progressionService.calculateLevel(it?.xp ?: 0)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
           initialValue = null
        )

    val currentXp : StateFlow<Long?> = userData
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



