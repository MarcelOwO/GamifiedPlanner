package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.shop.remote.ShopRemoteDataSource
import marcel.uni.gamifiedplanner.data.shop.remote.ShopRemoteDataSourceImpl
import marcel.uni.gamifiedplanner.data.shop.repository.ShopRepository
import marcel.uni.gamifiedplanner.data.shop.repository.ShopRepositoryImpl
import org.koin.dsl.module

class ShopModule {

    val shopModule = module{
        single<ShopRemoteDataSource> { ShopRemoteDataSourceImpl(get()) }
        single<ShopRepository> { ShopRepositoryImpl(get()) }
    }
}