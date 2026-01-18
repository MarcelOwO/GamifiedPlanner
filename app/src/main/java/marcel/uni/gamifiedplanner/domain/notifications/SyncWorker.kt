package marcel.uni.gamifiedplanner.domain.notifications

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.first
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.usecase.ObserveTasksUseCase

class SyncWorker(
    context: Context,
    params: WorkerParameters,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val logger: AppLogger,
    private val scheduler: TaskScheduler
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        logger.i("SyncWorker started")

        val tasks = observeTasksUseCase().first()

        tasks.forEach { task ->
            logger.i("Scheduling task with id: ${task.id}")
            scheduler.scheduleTask( task)
        }

        return Result.success()

    }
}