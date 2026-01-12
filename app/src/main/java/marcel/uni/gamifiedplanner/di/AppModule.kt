package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.ui.header.AppHeaderViewModel
import marcel.uni.gamifiedplanner.ui.profile.ProfileViewModel
import marcel.uni.gamifiedplanner.ui.settings.SettingsViewModel
import marcel.uni.gamifiedplanner.ui.stats.StatsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


// di modules I am unsure where to sort into
class AppModule {
    val appModule =
        module {
            viewModelOf(::AppHeaderViewModel)
            viewModelOf(::StatsViewModel)
            viewModelOf(::SettingsViewModel)
            viewModelOf(::ProfileViewModel)
        }
}
