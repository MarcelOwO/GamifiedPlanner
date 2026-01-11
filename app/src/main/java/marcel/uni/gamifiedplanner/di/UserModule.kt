package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.user.UserRepositoryImpl
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserInventoryUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.AddXpUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveLevelUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserAchievementsUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserTaskUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserUsernameUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveXpProgressUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveXpUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetUserNameUseCase
import org.koin.dsl.module

class UserModule {
    val userModule =
        module {
            single<UserRepository> { UserRepositoryImpl(get()) }
            factory { AddXpUseCase(get(), get()) }
            factory { ObserveDarkModeUseCase(get(), get()) }
            factory { ObserveLevelUseCase(get(), get()) }
            factory { ObserveNotificationStateUseCase(get(), get()) }
            factory { ObserveUserAchievementsUseCase(get(), get()) }
            factory { ObserveUserInventoryUseCase(get(), get()) }
            factory { ObserveUserUsernameUseCase(get(),get()) }
            factory { ObserveUserTaskUseCase(get(), get()) }
            factory { ObserveXpProgressUseCase(get(), get()) }
            factory { ObserveXpUseCase(get(), get()) }
            factory { PurchaseItemUseCase(get(), get(), get()) }
            factory { SetDarkModeUseCase(get(), get()) }
            factory { SetNotificationStateUseCase(get(), get()) }
            factory { SetUserNameUseCase(get(),get()) }
        }
}
