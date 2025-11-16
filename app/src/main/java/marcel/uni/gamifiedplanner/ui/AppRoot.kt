package marcel.uni.gamifiedplanner.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import marcel.uni.gamifiedplanner.ui.components.AppHeader
import marcel.uni.gamifiedplanner.ui.components.AppNavbar
import marcel.uni.gamifiedplanner.ui.navigation.AppNavHost

@Composable
fun AppRoot() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar={
            Column {
                AppHeader()
                AppNavbar(navController)
            }
        }
    ) { innerPadding->
        AppNavHost(
            navController,
            innerPadding)
    }
}
