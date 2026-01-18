package marcel.uni.gamifiedplanner.domain.notifications

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class TaskAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val taskTitle = intent?.getStringExtra("TASK_TITLE") ?: "Task Reminder"
        val taskId = intent?.getIntExtra("TASK_ID", 0) ?: 0
        val isEndSignal = intent?.getBooleanExtra("IS_END", false)
        if (isEndSignal == true) {
            notificationManager.cancel(taskId)
        } else {
            val builder = NotificationCompat.Builder(context, "task_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Task Reminder")
                .setContentText("Don't forget to complete: $taskTitle")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
            notificationManager.notify(taskId, builder.build())
        }


    }
}