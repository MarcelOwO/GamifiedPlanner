package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.auth.repository.FirebaseAuthRepository
import marcel.uni.gamifiedplanner.data.auth.FirebaseAuthRepositoryImpl
import marcel.uni.gamifiedplanner.domain.auth.usecase.AuthStatusUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.LogoutUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.login.LogInUseCase
import marcel.uni.gamifiedplanner.domain.auth.usecase.register.RegisterUseCase
import marcel.uni.gamifiedplanner.ui.auth.AuthViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


class AuthModule {
    val authModule = module {
        singleOf(::FirebaseAuthRepositoryImpl) { bind<FirebaseAuthRepository>() }
        factoryOf(::AuthStatusUseCase)
        factoryOf(::LogInUseCase)
        factoryOf(::RegisterUseCase)
        factoryOf(::LogoutUseCase)
        viewModelOf(::AuthViewModel)
    }
}