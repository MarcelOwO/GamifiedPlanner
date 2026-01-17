package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.achievement.AchievementRepositoryImpl
import marcel.uni.gamifiedplanner.domain.achievement.repository.AchievementRepository
import marcel.uni.gamifiedplanner.domain.achievement.service.AchievementEngine
import marcel.uni.gamifiedplanner.domain.achievement.usecase.ObserveAchievementsUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


class AchievementModule {
    val achievementModule =
        module {
            singleOf(::AchievementRepositoryImpl) {bind<AchievementRepository>()}
            factoryOf(::ObserveAchievementsUseCase)
            singleOf(::AchievementEngine)
        }
}
