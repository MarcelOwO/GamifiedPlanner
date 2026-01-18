package marcel.uni.gamifiedplanner.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import marcel.uni.gamifiedplanner.ui.home.HomeView
import marcel.uni.gamifiedplanner.ui.profile.ProfileView
import marcel.uni.gamifiedplanner.ui.settings.SettingsView
import marcel.uni.gamifiedplanner.ui.shop.ShopView
import marcel.uni.gamifiedplanner.ui.stats.StatsView

@Composable
fun AppNavHost(nav: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = nav,
        startDestination = AppRoutes.Home,
        modifier = Modifier.padding(padding)
    ) {
        composable(AppRoutes.Home) {
            HomeView()
        }
        composable(AppRoutes.Stats) {
            StatsView()
        }
        composable(AppRoutes.Shop) {
            ShopView()
        }
        composable(AppRoutes.Settings) {
            SettingsView()
        }
        composable(AppRoutes.Profile) {
            ProfileView()
        }

    }


}