package marcel.uni.gamifiedplanner.ui.shop


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.ui.components.ShopItemCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShopView(
    vm: ShopViewModel = koinViewModel()
) {
    val shopItems: List<ShopItem> by vm.shopItems.collectAsStateWithLifecycle()

    Column() {
        Text("Shop View")
    }

    Surface(modifier = Modifier.padding(10.dp)) {

        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            shopItems.forEach { item ->
                ShopItemCard(item)
            }
        }
    }
}