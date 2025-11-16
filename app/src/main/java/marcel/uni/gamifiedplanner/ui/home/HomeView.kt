package marcel.uni.gamifiedplanner.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeView(
    vm: HomeViewModel = viewModel()
) {

    val state by vm.state.collectAsState()

    Column(){
        Text("Home View")
    }

}
