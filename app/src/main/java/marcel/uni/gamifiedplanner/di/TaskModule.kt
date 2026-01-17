package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.domain.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.data.task.TaskRepositoryImpl
import marcel.uni.gamifiedplanner.domain.task.usecase.CreateTaskUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.DeleteTaskUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.ObserveTasksUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.UpdateTaskUseCase
import marcel.uni.gamifiedplanner.ui.home.HomeViewModel
import marcel.uni.gamifiedplanner.domain.task.usecase.ObserveTodaysTasksUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class TaskModule {

    val taskModule = module {
        singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
        factoryOf(::CreateTaskUseCase)
        factoryOf(::ObserveTasksUseCase)
        factoryOf(::ObserveTodaysTasksUseCase)
        factoryOf(::UpdateTaskUseCase)
        factoryOf(::DeleteTaskUseCase)
        viewModelOf(::HomeViewModel)
    }
}