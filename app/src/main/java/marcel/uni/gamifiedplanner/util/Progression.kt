package marcel.uni.gamifiedplanner.util

fun calculateLevel(xp: Long): Int = (xp.toDouble() / 100).toInt() + 1

fun calculateProgress(xp: Long): Long {
    val level = calculateLevel(xp)

    return xp - 100 * level
}
