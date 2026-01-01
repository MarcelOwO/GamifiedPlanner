package marcel.uni.gamifiedplanner.ui.header

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import marcel.uni.gamifiedplanner.domain.services.ProgressionService
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveLevelUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveXpUseCase

class AppHeaderViewModel(
    private val progressionService: ProgressionService,
    private val observeXp: ObserveXpUseCase,
    private val observeLevel: ObserveLevelUseCase,
) : ViewModel() {
    val currentLevel: StateFlow<Int> =
        observeLevel().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0,
        )

    val currentXp: StateFlow<Long?> =
        observeXp()
            .await()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null,
            )

    val xpProgress: StateFlow<Float> =
        userData
            .map { (it?.xp ?: 0) / 100f }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = 0f,
            )
}
