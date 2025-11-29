package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSource
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthDataSourceImpl
import marcel.uni.gamifiedplanner.domain.auth.usecase.AuthStatusUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.LogoutUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.login.LogInUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.register.RegisterUseCase
import marcel.uni.gamifiedplanner.ui.auth.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class AuthModule {
    val authModule = module {
        single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl(get()) }
        factory { AuthStatusUseCase(get()) }
        factory { LogInUseCase(get()) }
        factory { RegisterUseCase(get()) }
        factory { LogoutUseCase(get()) }
        viewModel { AuthViewModel(get(), get(), get(), get()) }
    }
}