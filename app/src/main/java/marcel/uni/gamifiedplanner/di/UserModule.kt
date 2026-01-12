package marcel.uni.gamifiedplanner.di

import android.system.Os.bind
import marcel.uni.gamifiedplanner.data.user.UserRepositoryImpl
import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserInventoryUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetDarkModeUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetNotificationStateUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.AddXpUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.CompleteTaskUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveLevelUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserAchievementsUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserTaskUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveUserUsernameUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveXpProgressUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.ObserveXpUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.SetUserNameUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class UserModule {
    val userModule =
        module {
            singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
            factoryOf(::AddXpUseCase)
            factoryOf(::CompleteTaskUseCase)
            factoryOf(::ObserveDarkModeUseCase)
            factoryOf(::ObserveLevelUseCase)
            factoryOf(::ObserveNotificationStateUseCase)
            factoryOf(::ObserveUserAchievementsUseCase)
            factoryOf(::ObserveUserInventoryUseCase)
            factoryOf(::ObserveUserUsernameUseCase)
            factoryOf(::ObserveUserTaskUseCase)
            factoryOf(::ObserveXpProgressUseCase)
            factoryOf(::ObserveXpUseCase)
            factoryOf(::PurchaseItemUseCase)
            factoryOf(::SetDarkModeUseCase)
            factoryOf(::SetNotificationStateUseCase)
            factoryOf(::SetUserNameUseCase)
        }
}
