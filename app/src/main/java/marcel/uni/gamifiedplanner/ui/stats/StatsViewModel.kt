package marcel.uni.gamifiedplanner.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.achievement.model.AchievementDisplay
import marcel.uni.gamifiedplanner.domain.achievement.usecase.ObserveAchievementsUseCase
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserAchievementsUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.tasks.ObserveUserTaskUseCase

class StatsViewModel(
    private val observeUserAchievements: ObserveUserAchievementsUseCase,
    private val observeAchievements: ObserveAchievementsUseCase,
    private val observeUserTask: ObserveUserTaskUseCase,
    private val logger: AppLogger
) : ViewModel() {

    val tasks: StateFlow<List<TaskHistoryItem>> = observeUserTask()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _achivements = MutableStateFlow<List<AchievementDisplay>>(emptyList())
    val achievements = _achivements.asStateFlow()

    init {
        fetchAchievements()
    }

    fun fetchAchievements() {
        viewModelScope.launch {
            val result = observeAchievements()
            _achivements.value = result.getOrNull() ?: emptyList()
        }
    }


}

