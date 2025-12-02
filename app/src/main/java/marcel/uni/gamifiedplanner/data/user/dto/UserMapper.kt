package marcel.uni.gamifiedplanner.data.user.dto

import marcel.uni.gamifiedplanner.domain.user.model.UserStats


fun UserDto.ToDomain() =
    UserStats(
        uid = this.uid,
        username = this.username,
        level = this.level,
        xp = this.xp,
        tasks = this.tasks,
        boughtItems = this.boughtItems,
        achievements = this.achievements,
        completedTasks = this.completedTasks
    );

fun UserStats.ToDto() =
    UserDto(
        uid = this.uid,
        username = this.username,
        level = this.level,
        xp = this.xp,
        tasks = this.tasks,
        boughtItems = this.boughtItems,
        achievements = this.achievements,
        completedTasks = this.completedTasks
    );