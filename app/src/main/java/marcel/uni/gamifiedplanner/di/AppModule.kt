package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.notifications.SyncWorker
import marcel.uni.gamifiedplanner.domain.notifications.TaskScheduler
import marcel.uni.gamifiedplanner.domain.notifications.usecase.CheckNotificationPermissionUseCase
import marcel.uni.gamifiedplanner.ui.header.AppHeaderViewModel
import marcel.uni.gamifiedplanner.ui.profile.ProfileViewModel
import marcel.uni.gamifiedplanner.ui.settings.SettingsViewModel
import marcel.uni.gamifiedplanner.ui.stats.StatsViewModel
import marcel.uni.gamifiedplanner.ui.theme.ThemeViewModel
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
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
            viewModelOf(::ThemeViewModel)

            workerOf(::SyncWorker)
            singleOf(::TaskScheduler)

            factoryOf(::CheckNotificationPermissionUseCase)
        }
}
