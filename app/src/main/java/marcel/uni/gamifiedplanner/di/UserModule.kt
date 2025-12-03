package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.user.repository.UserRepository
import marcel.uni.gamifiedplanner.data.user.repository.UserRepositoryImpl
import marcel.uni.gamifiedplanner.domain.user.usecase.GetUserDataUseCase
import marcel.uni.gamifiedplanner.domain.user.usecase.PurchaseItemUseCase
import org.koin.dsl.module

class UserModule {
    val userModule = module{
        single<UserRepository> { UserRepositoryImpl(get(), get()) }
        factory { GetUserDataUseCase(get()) }
        factory { PurchaseItemUseCase(get(),get(),get()) }
    }
}