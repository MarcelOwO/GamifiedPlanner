package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import marcel.uni.gamifiedplanner.domain.shop.model.ShopItem

@Composable
fun ShopItemCard(item: ShopItem,isOwned:Boolean=false, onClick: () -> Unit) {
    Button(shape= RoundedCornerShape(10.dp),
        modifier=Modifier.padding(10.dp).fillMaxWidth(),onClick= onClick){
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = item.name)
                HorizontalDivider(modifier=Modifier.padding(10.dp))
                Text(text = "${item.price} Coins")
                Spacer(modifier=Modifier.height(10.dp))
                Text(text = item.description)
            }
    }

}
