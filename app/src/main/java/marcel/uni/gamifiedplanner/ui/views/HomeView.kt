package marcel.uni.gamifiedplanner.ui.views

import androidx.compose.runtime.Composable
import marcel.uni.gamifiedplanner.ui.viewmodels.HomeViewModel

class HomeView(
    private val vm : HomeViewModel){
@Composable
fun Render(onNavigation: ()->Unit){

   val state  by vm.state.collectAsState()

}

}