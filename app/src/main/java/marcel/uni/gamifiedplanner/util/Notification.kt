package marcel.uni.gamifiedplanner.util

import android.Manifest
import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat

fun createNotificationChannel(context: Context) {
    val name = "Task Reminders"
    val description = "Notifications for task reminders"
    val importance = NotificationManager.IMPORTANCE_HIGH

    val channel = NotificationChannel("task_channel", name, importance).apply {
        this.description = description
    }

    val notificationManager =
        ContextCompat.getSystemService(context, NotificationManager::class.java) ?: return

    notificationManager.createNotificationChannel(channel)
}


@Composable
fun RequestNotificationPermission(onResult: (Boolean) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        onResult(granted)
    }

    LaunchedEffect(Unit) {
        launcher.launch(POST_NOTIFICATIONS)
    }

}

