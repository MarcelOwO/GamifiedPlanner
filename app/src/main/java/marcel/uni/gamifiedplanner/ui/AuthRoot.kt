package marcel.uni.gamifiedplanner.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import marcel.uni.gamifiedplanner.ui.navigation.AuthNavHost

// auth root ui
@Composable
fun AuthRoot() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        AuthNavHost(navController, innerPadding)
    }

}
