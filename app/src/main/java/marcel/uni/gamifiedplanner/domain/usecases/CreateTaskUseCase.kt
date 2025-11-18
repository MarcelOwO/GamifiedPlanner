package marcel.uni.gamifiedplanner.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import marcel.uni.gamifiedplanner.data.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.domain.models.Priority
import marcel.uni.gamifiedplanner.domain.models.Task
import marcel.uni.gamifiedplanner.domain.models.TaskStatus

class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
    ) = withContext(Dispatchers.IO) {
        val id = repository.getNewId()
        val now = System.currentTimeMillis()
        val task = Task(id, title, description, priority, status, createdAt = now)
        repository.createTask(task)
    }

}