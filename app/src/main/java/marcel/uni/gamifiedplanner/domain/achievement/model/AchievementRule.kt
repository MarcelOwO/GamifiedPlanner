package marcel.uni.gamifiedplanner.domain.achievement.model

interface AchievementRule {
    val achievementId:String
    fun matches(event:AchievementEvent):Boolean
}