package marcel.uni.gamifiedplanner.domain.task.usecase

import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository

class DeleteTaskUseCase(private val repo: TaskRepository) {

    suspend operator fun invoke(taskId: String): DeleteTaskResult {


        repo.deleteTask(taskId)
        return DeleteTaskResult.Success
    }
}