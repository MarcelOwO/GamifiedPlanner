package marcel.uni.gamifiedplanner.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsView(
    vm: SettingsViewModel = viewModel()
) {

    val state by vm.state.collectAsState()

    Column(){
        Text("Settings View")
    }

}