package marcel.uni.gamifiedplanner.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem
import marcel.uni.gamifiedplanner.domain.shop.usecase.GetShopItemsUseCase
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase

class ShopViewModel(
    private val getItemsUseCase: GetShopItemsUseCase,
    private val buyItemUseCase: PurchaseItemUseCase,
    ): ViewModel() {


    val shopItems: StateFlow<List<ShopItem>> = getItemsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList() // The initial List<Task>
        )



    }
