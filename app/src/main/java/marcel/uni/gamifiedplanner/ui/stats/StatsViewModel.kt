package marcel.uni.gamifiedplanner.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.UserAchievementItem
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserAchievementsUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.tasks.ObserveUserTaskUseCase

class StatsViewModel(
    private val observeUserAchievementsUseCase: ObserveUserAchievementsUseCase,
    private val observeUserTaskUseCase: ObserveUserTaskUseCase,

    private val logger: AppLogger

) : ViewModel() {


    val achievements: StateFlow<List<UserAchievementItem>> = observeUserAchievementsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val tasks: StateFlow<List<TaskHistoryItem>> = observeUserTaskUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

}

