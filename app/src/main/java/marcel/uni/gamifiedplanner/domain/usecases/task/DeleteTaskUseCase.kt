package marcel.uni.gamifiedplanner.domain.usecases.task

import marcel.uni.gamifiedplanner.data.task.repository.TaskRepository

class DeleteTaskUseCase(private val repo: TaskRepository) {

    suspend operator fun invoke(taskId: String): DeleteTaskResult {


        repo.deleteTask(taskId)
        return DeleteTaskResult.Success
    }
}