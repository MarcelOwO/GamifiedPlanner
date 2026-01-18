package marcel.uni.gamifiedplanner.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ThemeHost(vm: ThemeViewModel, content: @Composable () -> Unit) {
    val dark by vm.darkMode.collectAsState()
    GamifiedPlannerTheme(darkTheme = dark) {
        content()
    }
}