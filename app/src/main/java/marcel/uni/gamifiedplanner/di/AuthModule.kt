package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthRepositoryImpl
import marcel.uni.gamifiedplanner.domain.auth.usecase.AuthStatusUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.LogoutUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.login.LogInUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.register.RegisterUseCase
import marcel.uni.gamifiedplanner.ui.auth.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class AuthModule {
    val authModule = module {
        single<FirebaseAuthRepository> { FirebaseAuthRepositoryImpl(get()) }
        factory { AuthStatusUseCase(get()) }
        factory { LogInUseCase(get()) }
        factory { RegisterUseCase(get()) }
        factory { LogoutUseCase(get()) }
        viewModel { AuthViewModel(get(), get(), get(), get()) }
    }
}