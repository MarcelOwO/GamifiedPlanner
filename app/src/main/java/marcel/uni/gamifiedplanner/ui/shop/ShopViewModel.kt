package marcel.uni.gamifiedplanner.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem
import marcel.uni.gamifiedplanner.domain.shop.usecase.GetShopItemsUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserInventoryUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase
import marcel.uni.gamifiedplanner.util.PlannerResult

class ShopViewModel(
    private val getItemsUseCase: GetShopItemsUseCase,
    private val getInventoryUseCase: ObserveUserInventoryUseCase,
    private val purchaseItemUseCase: PurchaseItemUseCase,
    private val logger: AppLogger,
) : ViewModel() {
    private val _selectedFilter = MutableStateFlow("All")
    val selectedFilter = _selectedFilter.asStateFlow()

    val inventoryIds: StateFlow<List<String>> =
        getInventoryUseCase()
            .map { inventoryList ->
                inventoryList.map { item ->
                    item.itemId
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList(),
            )

    private val shopItems: StateFlow<List<ShopItem>> =
        getItemsUseCase().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList(),
        )

    val filteredItems: StateFlow<List<ShopItem>> =
        combine(shopItems, inventoryIds, selectedFilter) { items, owned, filter ->
            if (filter == "Inventory") {
                val ownedSet = owned.map { it.trim() }.toSet()
                items.filter { it.id.trim() in ownedSet }
            } else {
                items
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList(),
        )


    fun setFilter(filter: String) {
        _selectedFilter.value = filter
    }

    fun buyItem(
        itemId: String,
        onResult: (PlannerResult<Unit>) -> Unit,
    ) {
        viewModelScope.launch {
            logger.i("Attempting to buy item with id: $itemId")
            purchaseItemUseCase(itemId).also { result ->
                onResult(result)
            }
        }
    }
}
