package marcel.uni.gamifiedplanner.data.user.dto.mapper

import marcel.uni.gamifiedplanner.data.user.dto.UserDto
import marcel.uni.gamifiedplanner.data.user.dto.UserSettingsDto
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.user.model.TaskHistoryItem
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserInventory
import marcel.uni.gamifiedplanner.domain.user.model.UserProfile
import marcel.uni.gamifiedplanner.domain.user.model.UserSettings
import marcel.uni.gamifiedplanner.domain.user.model.UserStats


fun UserDto.ToDomain(): User {
    return User(
        uid = this.uid,

        profile = UserProfile(
            username = this.username,
        ),
        stats = UserStats(
            xp = this.xp,
            currency = this.currency,
        ),
        inventory = UserInventory(
            itemIds = this.purchasedItems.keys,
            achievements = this.completedAchievements,
            tasks = this.taskLog.mapValues {
                it.value.ToDomain()
            }),
        settings = UserSettings(
            this.settings.darkMode,
            this.settings.notifications,
        )
    )
}


fun User.ToDto() =
    UserDto(
 uid = this.uid,
 username =this.profile.username,
 xp=this.stats.xp,
 currency=stats.currency,
 settings= UserSettingsDto(
     darkMode = this.settings.darkMode,
     notifications = this.settings.notifications,
 ),
 purchasedItems=this.inventory.itemIds.associateWith{false},
 completedAchievements =this.inventory.achievements,
 taskLog=this.inventory.tasks.mapValues{it.value.ToDto()}, )