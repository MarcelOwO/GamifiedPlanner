package marcel.uni.gamifiedplanner.ui.header

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.model.UserStats
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveLevelUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.ObserveStreakUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.ObserveXpProgressUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.ObserveXpUseCase
import marcel.uni.gamifiedplanner.util.calculateProgress

class AppHeaderViewModel(
    private val observeXp: ObserveXpUseCase,
    private val observeLevel: ObserveLevelUseCase,
    private val observeXpProgress: ObserveXpProgressUseCase,
    private val observeStreak: ObserveStreakUseCase,
    private val logger: AppLogger
) : ViewModel() {
    val currentLevel: StateFlow<Int> =
        observeLevel().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0,
        )

    val currentXp: StateFlow<Long> =
        observeXp().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0,
        )


    val xpProgress: StateFlow<Long> =
        observeXpProgress().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0,
        )

    val currentStreak: StateFlow<Long> =
        observeStreak().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0,
        )

}
