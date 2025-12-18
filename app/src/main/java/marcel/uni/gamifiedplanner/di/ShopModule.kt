package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.data.shop.repository.ShopRepositoryImpl
import marcel.uni.gamifiedplanner.domain.shop.usecase.GetShopItemsUseCase
import marcel.uni.gamifiedplanner.ui.shop.ShopViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class ShopModule {
    val shopModule = module{
        single<ShopRepository> { ShopRepositoryImpl(get()) }
        factory{ GetShopItemsUseCase(get())}
        viewModel { ShopViewModel(get(),get(),get()) }
    }
}