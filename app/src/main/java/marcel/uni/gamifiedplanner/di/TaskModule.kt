package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.data.task.TaskRepositoryImpl
import marcel.uni.gamifiedplanner.domain.task.usecase.CreateTaskUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.DeleteTaskUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.GetTasksUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.UpdateTaskUseCase
import marcel.uni.gamifiedplanner.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class TaskModule {

    val taskModule = module {
        single<TaskRepository> { TaskRepositoryImpl(get(), get()) }
        factory { CreateTaskUseCase(get(),get()) }
        factory { GetTasksUseCase(get(),get()) }
        factory { UpdateTaskUseCase(get(),get(),get()) }
        factory { DeleteTaskUseCase(get(),get()) }

        viewModel { HomeViewModel(get(),get(),get(),get()) }
    }
}