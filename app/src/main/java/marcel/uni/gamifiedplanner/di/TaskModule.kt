package marcel.uni.gamifiedplanner.di

import marcel.uni.gamifiedplanner.data.task.remote.TaskRemoteDataSource
import marcel.uni.gamifiedplanner.data.task.remote.TaskRemoteDataSourceImpl
import marcel.uni.gamifiedplanner.data.task.repository.TaskRepository
import marcel.uni.gamifiedplanner.data.task.repository.TaskRepositoryImpl
import org.koin.dsl.module

class TaskModule {

    val taskModule = module {
        single<TaskRemoteDataSource> { TaskRemoteDataSourceImpl(get()) }
        single<TaskRepository> { TaskRepositoryImpl(get(), get()) }
    }
}