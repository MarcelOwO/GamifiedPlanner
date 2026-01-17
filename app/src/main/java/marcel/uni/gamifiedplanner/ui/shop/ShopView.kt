package marcel.uni.gamifiedplanner.ui.shop


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import marcel.uni.gamifiedplanner.ui.components.CustomSelect
import marcel.uni.gamifiedplanner.ui.components.ShopItemCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShopView(
    vm: ShopViewModel = koinViewModel(),
) {
    val shopItems by vm.shopItems.collectAsStateWithLifecycle()
    val inventoryIds by vm.inventoryIds.collectAsStateWithLifecycle()
    val selectedFilter by vm.selectedFilter.collectAsStateWithLifecycle()

    val ownedSet = inventoryIds
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .toSet()

    Column(modifier = Modifier.fillMaxSize().padding(5.dp)) {
        Text(
            "Shop",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp)
        )

        CustomSelect(listOf("All", "Inventory"), selected = selectedFilter, onSelect = {
            vm.setFilter(it)
        })

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(shopItems) { item ->
                val itemId = item.id.trim()
                val isOwned = itemId.isNotEmpty() && ownedSet.contains(itemId)
                ShopItemCard(
                    item = item,
                    isOwned = isOwned,
                    onClick = {
                        if (!isOwned) {
                            vm.buyItem(item.id, onResult = ({
                            }))
                        }
                    }
                )
            }
        }
    }
}

