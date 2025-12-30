package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.user.repository.UserRepositoryImpl
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserDataUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserInventoryUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetNotificationStateUseCase
import org.koin.dsl.module

class UserModule {
    val userModule =
        module {
            single<UserRepository> { UserRepositoryImpl(get(), get()) }
            factory { ObserveUserDataUseCase(get()) }
            factory { PurchaseItemUseCase(get(), get(), get()) }
            factory { ObserveUserInventoryUseCase(get()) }
            factory { SetDarkModeUseCase(get()) }
            factory { SetNotificationStateUseCase(get()) }
            factory { ObserveDarkModeUseCase(get()) }
            factory { ObserveNotificationStateUseCase(get()) }
        }
}
