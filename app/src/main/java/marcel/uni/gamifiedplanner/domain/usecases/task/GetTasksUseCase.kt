package marcel.uni.gamifiedplanner.domain.usecases.task

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.data.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.models.Task

class GetTasksUseCase(
    private val repo: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> = repo.observeTasks()
}