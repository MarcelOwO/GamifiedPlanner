package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.user.UserRepositoryImpl
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserInventoryUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetNotificationStateUseCase
import org.koin.dsl.module

class UserModule {
    val userModule =
        module {
            single<UserRepository> { UserRepositoryImpl(get()) }
            factory { PurchaseItemUseCase(get(), get(),get()) }
            factory { ObserveUserInventoryUseCase(get(),get()) }
            factory { SetDarkModeUseCase(get(),get()) }
            factory { SetNotificationStateUseCase(get()) }
            factory { ObserveDarkModeUseCase(get(),get()) }
            factory { ObserveNotificationStateUseCase(get(),get()) }
        }
}
