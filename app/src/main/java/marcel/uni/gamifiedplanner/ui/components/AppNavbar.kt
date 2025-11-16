package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import marcel.uni.gamifiedplanner.ui.navigation.AppRoutes

@Composable
fun AppNavbar(nav: NavHostController) {
    Surface(shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier.padding(10.dp),
        ) {
        Row(modifier=Modifier.fillMaxWidth().padding(5.dp),
            horizontalArrangement=Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            NavButton("Home", AppRoutes.Home, nav)
            NavButton("Shop", AppRoutes.Shop, nav)
            NavButton("Stats", AppRoutes.Stats, nav)
            NavButton("Settings", AppRoutes.Settings, nav)
            NavButton("Profile", AppRoutes.Profile, nav)
        }
    }
}