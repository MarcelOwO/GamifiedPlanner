package marcel.uni.gamifiedplanner.util

import com.google.firebase.Timestamp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Timestamp.toFormatedString(): String {
    val instant = Instant.ofEpochSecond(this.seconds, this.nanoseconds.toLong())
    val formatter = DateTimeFormatter.ofPattern("dd:MM:YYYY HH:mm").withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}

