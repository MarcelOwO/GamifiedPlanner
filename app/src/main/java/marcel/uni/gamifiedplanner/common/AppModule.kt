package marcel.uni.gamifiedplanner.common

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import marcel.uni.gamifiedplanner.ui.viewmodels.HomeViewModel
import marcel.uni.gamifiedplanner.ui.viewmodels.LoginViewModel
import marcel.uni.gamifiedplanner.ui.viewmodels.RegisterViewModel
import marcel.uni.gamifiedplanner.ui.viewmodels.SettingsViewModel
import marcel.uni.gamifiedplanner.ui.viewmodels.ShopViewModel
import marcel.uni.gamifiedplanner.ui.viewmodels.StatsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class AppModule {
    val appModule = module {

        single{FirebaseAuth.getInstance()}
        single {FirebaseFirestore.getInstance()}

        viewModel { HomeViewModel() }
        viewModel { ShopViewModel() }
        viewModel { StatsViewModel() }
        viewModel { SettingsViewModel() }

        viewModel { RegisterViewModel() }
        viewModel { LoginViewModel() }
    }
}