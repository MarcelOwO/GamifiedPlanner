package marcel.uni.gamifiedplanner.domain.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.model.Task

class TaskScheduler(
    private val logger: AppLogger
) {
    fun scheduleTask(
        context: Context,
        task: Task,
    ) {
        val appCtx = context.applicationContext
        runCatching {
            logger.i("Scheduling task alarms for task: ${task.title}")
            val alarmManager = appCtx.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val startMs: Long = task.startTime?.toInstant()?.toEpochMilli() ?: 0L
            val durationMs: Long = task.duration?.times(60L)?.times(1000) ?: 0L
            val endMs: Long = startMs + durationMs
            val taskId = task.id.hashCode()

            val startIntent = createPendingIntent(appCtx, taskId, task.title, false)
            val endIntent = createPendingIntent(appCtx, taskId, task.title, true)

            if (startMs > System.currentTimeMillis()) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    startMs,
                    startIntent
                )
            }
            if (durationMs > 0 && endMs > System.currentTimeMillis()) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, endMs, endIntent)
            }
        }.onFailure { e ->
            logger.e("Failed to schedule task alarms for task: ${task.title} with ${e.message}")
        }
    }

    fun cancelTask(
        context: Context,
        taskId: String
    ) {
        val appCtx = context.applicationContext
        runCatching {
            logger.i("Cancelling task alarms for taskId: $taskId")
            val alarmManager = appCtx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val taskHashId = taskId.hashCode()

            val startIntent = getPendingIntentForId(appCtx, taskHashId, false)
            val endIntent = getPendingIntentForId(appCtx, taskHashId, true)

            alarmManager.cancel(startIntent)
            alarmManager.cancel(endIntent)

        }.onFailure { e ->
            logger.e("Failed to cancel task alarms for taskId: $taskId with ${e.message}")
        }
    }

    private fun createPendingIntent(
        context: Context,
        id: Int,
        title: String,
        isEnd: Boolean
    ): PendingIntent {
        val intent = Intent(context, TaskAlarmReceiver::class.java).apply {
            putExtra("TASK_TITLE", title)
            putExtra("TASK_ID", id)
            putExtra("IS_END", isEnd)
        }

        val requestCode = if (isEnd) (id * 2) + 1 else id * 2
        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun getPendingIntentForId(
        context: Context,
        taskId: Int,
        isEnd: Boolean
    ): PendingIntent {

        val intent = Intent(context, TaskAlarmReceiver::class.java).apply {
            putExtra("TASK_ID", taskId)
            putExtra("IS_END", isEnd)
        }

        val requestCode = if (isEnd) (taskId * 2) + 1 else taskId * 2

        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}