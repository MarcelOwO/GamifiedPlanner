package marcel.uni.gamifiedplanner.ui.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

@Composable
fun AppNavbar(nav: NavHostController) {
    var selectedOption by remember { mutableStateOf("Home") }
    CustomSelect(
        options = listOf("Home", "Shop", "Stats", "Settings", "Profile"),
        selectedOption,
        onSelect = { selected ->
            selectedOption = selected
            nav.navigate(selected)
        })
}