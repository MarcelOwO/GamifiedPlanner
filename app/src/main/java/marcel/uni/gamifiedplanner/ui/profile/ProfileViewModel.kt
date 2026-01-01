package marcel.uni.gamifiedplanner.ui.profile

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserUsernameUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetUserNameUseCase
import marcel.uni.gamifiedplanner.util.PlannerResult


class ProfileViewModel(
    private val observeUsernameUseCase: ObserveUserUsernameUseCase,
    private val setUsernameUseCase: SetUserNameUseCase,
) : ViewModel(){

    fun UpdateUsername(new:String):PlannerResult<Nothing>{
         if (new.isBlank()){
             return PlannerResult.ValidationError("Name can't be empty");
         }

         viewModelScope.launch{
             setUsernameUseCase(new)
         }

        return PlannerResult.Success<Nothing>()
    }

    val username :StateFlow<String> = observeUsernameUseCase()
        .stateIn(scope=viewModelScope,
                            started= SharingStarted.WhileSubscribed(5000),
                            initialValue="",
        );




}
