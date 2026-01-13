package marcel.uni.gamifiedplanner.di

import android.system.Os.bind
import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.data.shop.ShopRepositoryImpl
import marcel.uni.gamifiedplanner.domain.shop.usecase.GetShopItemsUseCase
import marcel.uni.gamifiedplanner.ui.shop.ShopViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class ShopModule {
    val shopModule = module {
        singleOf(::ShopRepositoryImpl) { bind<ShopRepository>() }
        factoryOf(::GetShopItemsUseCase)
        viewModelOf(::ShopViewModel)
    }
}