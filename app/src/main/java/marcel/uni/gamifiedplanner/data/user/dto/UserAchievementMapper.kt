package marcel.uni.gamifiedplanner.data.user.dto

import marcel.uni.gamifiedplanner.domain.user.model.UserAchievement

fun UserAchievementDto.toDomain()= UserAchievement(
 uuid  =  this.uuid,
 achievementId = this.achievementId,
 unlockedAt =this.unlockedAt,
)

fun UserAchievement.toDto()= UserAchievementDto(
 uuid  =  this.uuid,
 achievementId = this.achievementId,
 unlockedAt =this.unlockedAt,
)