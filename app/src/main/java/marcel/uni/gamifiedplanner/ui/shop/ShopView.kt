package marcel.uni.gamifiedplanner.ui.shop


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShopView(
    vm: ShopViewModel = koinViewModel()
) {

    val state by vm.state.collectAsState()

    Column(){
        Text("Shop View")
    }

}