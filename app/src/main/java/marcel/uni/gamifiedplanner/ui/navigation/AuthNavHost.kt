package marcel.uni.gamifiedplanner.ui.navigation


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import marcel.uni.gamifiedplanner.ui.auth.LoginView
import marcel.uni.gamifiedplanner.ui.auth.RegisterView

@Composable
fun AuthNavHost(nav: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = nav,
        startDestination = AppRoutes.Login,
        modifier = Modifier.padding(padding)
    ) {
        composable(AppRoutes.Login) {
            LoginView(nav)
        }
        composable(AppRoutes.Register) {
            RegisterView(nav)
        }
    }
}