package marcel.uni.gamifiedplanner.ui.components

import android.view.Surface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem

@Composable
fun ShopItemCard(item: ShopItem) {

    Surface(modifier = Modifier.padding(10.dp)) {

        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = item.name)
            Text(text = "${item.price} Coins")
            Text(text = item.description)
        }
    }
}

@Composable
fun Colum(
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal,
    verticalArrangement: Arrangement.HorizontalOrVertical
) {
    TODO("Not yet implemented")
}