package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.user.UserRepositoryImpl
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveLevelUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserAchievementsUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserInventoryUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.profile.ObserveEmailUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.profile.ObserveUserUsernameUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.profile.SetEmailUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.profile.SetUserNameUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.settings.ObserveDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.settings.ObserveNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.settings.SetDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.settings.SetNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.AddBalanceUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.AddXpUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.IncrementStreakUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.ObserveBalanceUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.ObserveStreakUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.ObserveXpProgressUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.stats.ObserveXpUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.tasks.CompleteTaskUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.tasks.ObserveTodaysUsersTaskUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.tasks.ObserveUserTaskUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class UserModule {
    val userModule =
        module {
            singleOf(::UserRepositoryImpl) { bind<UserRepository>() }

            //shop
            factoryOf(::PurchaseItemUseCase)

            //task
            factoryOf(::CompleteTaskUseCase)

            //settings
            factoryOf(::ObserveDarkModeUseCase)
            factoryOf(::SetNotificationStateUseCase)
            factoryOf(::ObserveNotificationStateUseCase)
            factoryOf(::SetDarkModeUseCase)

            //stats
            factoryOf(::ObserveLevelUseCase)

            factoryOf(::ObserveXpProgressUseCase)
            factoryOf(::AddXpUseCase)
            factoryOf(::ObserveXpUseCase)

            factoryOf(::AddBalanceUseCase)
            factoryOf(::ObserveBalanceUseCase)
            factoryOf(
                ::ObserveStreakUseCase
            )
            factoryOf(::IncrementStreakUseCase)

            //inventory
            factoryOf(::ObserveUserAchievementsUseCase)
            factoryOf(::ObserveUserInventoryUseCase)
            factoryOf(::ObserveUserTaskUseCase)
            factoryOf(::ObserveTodaysUsersTaskUseCase)

            //profile
            factoryOf(::SetUserNameUseCase)
            factoryOf(::ObserveUserUsernameUseCase)
            factoryOf(::ObserveEmailUseCase)
            factoryOf(::SetEmailUseCase)
        }
}
