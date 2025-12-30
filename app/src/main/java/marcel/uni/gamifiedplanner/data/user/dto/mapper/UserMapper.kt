package marcel.uni.gamifiedplanner.data.user.dto.mapper

import marcel.uni.gamifiedplanner.data.user.dto.UserDto
import marcel.uni.gamifiedplanner.domain.user.model.User
import marcel.uni.gamifiedplanner.domain.user.model.UserInventory
import marcel.uni.gamifiedplanner.domain.user.model.UserProfile
import marcel.uni.gamifiedplanner.domain.user.model.UserSettings
import marcel.uni.gamifiedplanner.domain.user.model.UserStats


fun UserDto.toDomain(): User {
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
