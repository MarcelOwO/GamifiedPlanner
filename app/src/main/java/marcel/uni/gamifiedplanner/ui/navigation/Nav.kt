package marcel.uni.gamifiedplanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostContainer (navController: NavHostController){
   NavHost(
    navController = navController,
    startDestination ="home"
   ){
      composable("home"){



      }

   }




    
}