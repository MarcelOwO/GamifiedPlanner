package marcel.uni.gamifiedplanner.domain.services

import kotlin.math.sqrt

class ProgressionService {
    fun calculateLevel( xp: Long): Int{
       return (sqrt(xp.toDouble()) *0.1).toInt() +1
    }
}