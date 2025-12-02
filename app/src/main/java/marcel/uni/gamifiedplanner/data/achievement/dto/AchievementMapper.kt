package marcel.uni.gamifiedplanner.data.achievement.dto

import marcel.uni.gamifiedplanner.domain.achievement.model.Achievement

class AchievementMapper {
}

fun AchievementDto.ToDomain(): Achievement =
    Achievement(
        id = this.id,
        name = this.name,
        description = this.description,
        iconUrl = this.iconUrl,
    )

fun Achievement.ToDto(): AchievementDto =
    AchievementDto(
        id = this.id,
        name = this.name,
        description = this.description,
        iconUrl = this.iconUrl,
    )

