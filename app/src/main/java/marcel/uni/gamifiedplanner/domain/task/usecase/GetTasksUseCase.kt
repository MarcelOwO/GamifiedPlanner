package marcel.uni.gamifiedplanner.domain.task.usecase

import kotlinx.coroutines.flow.Flow
import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.task.model.Task

class GetTasksUseCase(
    private val repo: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> = repo.observeTasks()
}