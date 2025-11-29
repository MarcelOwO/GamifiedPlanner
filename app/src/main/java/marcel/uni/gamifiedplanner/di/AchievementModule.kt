package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.data.achievement.repository.AchievementRepositoryImpl
import org.koin.dsl.module

class AchievementModule {
    val achievementModule = module {
        single<AchievementRepository> { AchievementRepositoryImpl(get()) }
    }
}