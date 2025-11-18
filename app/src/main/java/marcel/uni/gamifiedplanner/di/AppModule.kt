package marcel.uni.gamifiedplanner.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import marcel.uni.gamifiedplanner.ui.header.AppHeaderViewModel
import marcel.uni.gamifiedplanner.ui.home.HomeViewModel
import marcel.uni.gamifiedplanner.ui.profile.ProfileViewModel
import marcel.uni.gamifiedplanner.ui.settings.SettingsViewModel
import marcel.uni.gamifiedplanner.ui.shop.ShopViewModel
import marcel.uni.gamifiedplanner.ui.stats.StatsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class AppModule {
    val appModule = module {
        viewModel { HomeViewModel() }
        viewModel { AppHeaderViewModel() }
        viewModel { ShopViewModel() }
        viewModel { StatsViewModel() }
        viewModel { SettingsViewModel() }
        viewModel { ProfileViewModel() }

    }
}