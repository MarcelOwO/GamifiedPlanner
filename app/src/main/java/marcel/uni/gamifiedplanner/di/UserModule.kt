package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.user.remote.UserRemoteDataSource
import marcel.uni.gamifiedplanner.data.user.remote.UserRemoteDataSourceImpl
import marcel.uni.gamifiedplanner.data.user.repository.UserRepository
import marcel.uni.gamifiedplanner.data.user.repository.UserRepositoryImpl
import marcel.uni.gamifiedplanner.domain.usecases.user.GetUserDataUseCase
import org.koin.dsl.module

class UserModule {
    val userModule = module{
        single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get(), get()) }
        factory { GetUserDataUseCase(get()) }
    }
}