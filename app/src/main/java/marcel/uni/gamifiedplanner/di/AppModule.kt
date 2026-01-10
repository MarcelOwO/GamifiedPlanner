package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.ui.header.AppHeaderViewModel
import marcel.uni.gamifiedplanner.ui.profile.ProfileViewModel
import marcel.uni.gamifiedplanner.ui.settings.SettingsViewModel
import marcel.uni.gamifiedplanner.ui.shop.ShopViewModel
import marcel.uni.gamifiedplanner.ui.stats.StatsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

//
// Remaining modules get dumped here if I don't know where else to save them
//

class AppModule {
    val appModule =
        module {
            viewModel { AppHeaderViewModel(get(), get(),get()) }
            viewModel { StatsViewModel() }
            viewModel { SettingsViewModel(get(), get(), get(), get(), get()) }
            viewModel { ProfileViewModel(get(),get()) }
        }
}
