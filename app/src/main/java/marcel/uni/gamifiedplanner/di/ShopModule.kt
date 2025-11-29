package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.data.shop.repository.ShopRepositoryImpl
import org.koin.dsl.module

class ShopModule {
    val shopModule = module{
        single<ShopRepository> { ShopRepositoryImpl(get()) }
    }
}