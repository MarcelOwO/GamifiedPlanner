package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.task.remote.TaskRemoteDataSource
import marcel.uni.gamifiedplanner.data.task.remote.TaskRemoteDataSourceImpl
import marcel.uni.gamifiedplanner.data.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.data.task.repository.TaskRepositoryImpl
import marcel.uni.gamifiedplanner.domain.usecases.task.CreateTaskUseCase
import marcel.uni.gamifiedplanner.domain.usecases.task.DeleteTaskUseCase
import marcel.uni.gamifiedplanner.domain.usecases.task.GetTasksUseCase
import marcel.uni.gamifiedplanner.domain.usecases.task.UpdateTaskUseCase
import marcel.uni.gamifiedplanner.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class TaskModule {

    val taskModule = module {
        single<TaskRemoteDataSource> { TaskRemoteDataSourceImpl(get()) }
        single<TaskRepository> { TaskRepositoryImpl(get(), get()) }
        factory { CreateTaskUseCase(get()) }
        factory { GetTasksUseCase(get()) }
        factory { UpdateTaskUseCase(get()) }
        factory { DeleteTaskUseCase(get()) }

        viewModel { HomeViewModel(get(),get(),get(),get()) }
    }
}