package marcel.uni.gamifiedplanner.domain.task.model

enum class Priority {
    LOW,
    MEDIUM,
    HIGH,
}

fun Priority.toString(): String{
    return when(this){
        Priority.LOW -> "Low"
        Priority.MEDIUM -> "Medium"
        Priority.HIGH -> "High"
    }
}