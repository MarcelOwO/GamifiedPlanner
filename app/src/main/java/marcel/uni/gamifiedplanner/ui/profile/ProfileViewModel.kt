package marcel.uni.gamifiedplanner.ui.profile

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.user.usecase.profile.ObserveEmailUseCase

import marcel.uni.gamifiedplanner.domain.user.usecase.profile.ObserveUserUsernameUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.profile.SetEmailUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.profile.SetUserNameUseCase
import marcel.uni.gamifiedplanner.util.PlannerResult


class ProfileViewModel(
    private val observeUsernameUseCase: ObserveUserUsernameUseCase,
    private val observeEmailUseCase: ObserveEmailUseCase,
    private val setUsernameUseCase: SetUserNameUseCase,
    private val setEmailUseCase: SetEmailUseCase,
    private val logger: AppLogger
) : ViewModel() {

    fun UpdateUsername(new: String): PlannerResult<Unit> {
        logger.i("Updating username")
        if (new.isBlank()) {
            logger.e("Name can't be empty")
            return PlannerResult.Error("Name can't be empty");
        }
        viewModelScope.launch {
            setUsernameUseCase(new)
        }
        return PlannerResult.Success(Unit)
    }

    val username: StateFlow<String> = observeUsernameUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "",
        );

    fun UpdateEmail(new: String): PlannerResult<Unit> {
        logger.i("Updating email")
        if (new.isBlank()) {
            logger.e("Email can't be empty")
            return PlannerResult.Error("Email can't be empty");
        }
        viewModelScope.launch {
            setEmailUseCase(new)
        }
        return PlannerResult.Success(Unit)
    }

    val email: StateFlow<String> = observeEmailUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "",
        );

}
