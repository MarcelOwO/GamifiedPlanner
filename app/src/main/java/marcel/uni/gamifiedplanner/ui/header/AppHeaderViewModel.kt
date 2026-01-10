package marcel.uni.gamifiedplanner.ui.header

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveLevelUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveXpUseCase
import marcel.uni.gamifiedplanner.util.calculateProgress

class AppHeaderViewModel(
    private val observeXp: ObserveXpUseCase,
    private val observeLevel: ObserveLevelUseCase,
) : ViewModel() {
    val currentLevel: StateFlow<Int> =
        observeLevel().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0,
        )

    val currentXp: StateFlow<Long> =
        flow {
            val xp = observeXp()
            emit(xp)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0f,
        )

    val xpProgress: StateFlow<Float> =
        flow {
            val xp = observeXp()
            emit(xp)
        }.map { xp -> calculateProgress(xp) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = 0f,
            )
}
