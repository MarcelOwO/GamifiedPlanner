package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.user.repository.UserRepositoryImpl
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.user.usecase.GetDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.GetNotificationsUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.GetUserDataUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.GetUserInventoryUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ToggleDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ToggleNotificationsUseCase
import org.koin.dsl.module

class UserModule {
    val userModule =
        module {
            single<UserRepository> { UserRepositoryImpl(get(), get()) }
            factory { GetUserDataUseCase(get()) }
            factory { PurchaseItemUseCase(get(), get(), get()) }
            factory { GetUserInventoryUseCase(get()) }
            factory { ToggleDarkModeUseCase(get()) }
            factory { ToggleNotificationsUseCase(get()) }
            factory { GetDarkModeUseCase(get()) }
            factory { GetNotificationsUseCase(get()) }
        }
}
