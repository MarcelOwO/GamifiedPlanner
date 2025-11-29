package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.achievement.remote.AchievementRemoteDataSource
import marcel.uni.gamifiedplanner.data.achievement.remote.AchievementRemoteDataSourceImpl
import marcel.uni.gamifiedplanner.data.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.data.achievement.repository.AchievementRepositoryImpl
import org.koin.dsl.module

class AchievementModule {
    val achievementModule = module {
        single<AchievementRemoteDataSource> { AchievementRemoteDataSourceImpl(get()) }
        single<AchievementRepository> { AchievementRepositoryImpl(get()) }
    }
}