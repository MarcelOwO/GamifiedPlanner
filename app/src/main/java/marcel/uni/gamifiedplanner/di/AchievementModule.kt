package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.achievement.repository.AchievementRepositoryImpl
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.achievement.usecase.GetAchievementsUseCase
import org.koin.dsl.module

class AchievementModule {
    val achievementModule =
        module {
            single<AchievementRepository> { AchievementRepositoryImpl(get()) }
            factory { GetAchievementsUseCase(get(), get(), get()) }
        }
}
