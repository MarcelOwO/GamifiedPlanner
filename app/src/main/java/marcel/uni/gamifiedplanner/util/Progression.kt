package marcel.uni.gamifiedplanner.util

import marcel.uni.gamifiedplanner.domain.task.model.Priority

// simple enough level progression with no scaling or anything
fun calculateLevel(xp: Long): Int = (xp.toDouble() / 100).toInt() + 1

// needed for progress bar
fun calculateProgress(xp: Long): Long {
    val level = calculateLevel(xp)

    return xp - 100 * level
}

fun calculateXp(taskPriority: Priority):Long{
    return when(taskPriority){
        Priority.LOW -> 10
        Priority.MEDIUM -> 50
        Priority.HIGH -> 100
    }
}
