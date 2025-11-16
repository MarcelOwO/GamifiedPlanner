package marcel.uni.gamifiedplanner.common

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import marcel.uni.gamifiedplanner.ui.home.HomeViewModel
import marcel.uni.gamifiedplanner.ui.profile.ProfileView
import marcel.uni.gamifiedplanner.ui.profile.ProfileViewModel
import marcel.uni.gamifiedplanner.ui.viewmodels.LoginViewModel
import marcel.uni.gamifiedplanner.ui.viewmodels.RegisterViewModel
import marcel.uni.gamifiedplanner.ui.settings.SettingsViewModel
import marcel.uni.gamifiedplanner.ui.shop.ShopViewModel
import marcel.uni.gamifiedplanner.ui.stats.StatsViewModel
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
        viewModel { ProfileViewModel() }

    }
}