package marcel.uni.gamifiedplanner.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import marcel.uni.gamifiedplanner.ui.auth.AuthViewModel
import org.koin.androidx.compose.koinViewModel

//
// Root of the ui and to seperate the auth views from the rest
//

@Composable
fun RootView(
    vm: AuthViewModel = koinViewModel()
) {
    val isLoggedIn = vm.isLoggedIn.collectAsState(false)

    if (isLoggedIn.value) {
        AppRoot()
    } else {
        AuthRoot()
    }
}
