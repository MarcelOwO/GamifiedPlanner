package marcel.uni.gamifiedplanner.data.user.dto

import marcel.uni.gamifiedplanner.domain.models.UserData


fun UserDto.ToDomain() =
    UserData(
        uid = this.uid,
        username = this.username,
        level = this.level,
        xp = this.xp,
        tasks = this.tasks,
        boughtItems = this.boughtItems,
        achievements = this.achievements,
        completedTasks = this.completedTasks
    );

fun UserData.ToDto() =
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