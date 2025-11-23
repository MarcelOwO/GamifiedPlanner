package marcel.uni.gamifiedplanner.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.models.Priority
import marcel.uni.gamifiedplanner.domain.models.Task
import marcel.uni.gamifiedplanner.domain.models.TaskStatus
import marcel.uni.gamifiedplanner.domain.usecases.task.CreateTaskResult
import marcel.uni.gamifiedplanner.domain.usecases.task.CreateTaskUseCase
import marcel.uni.gamifiedplanner.domain.usecases.task.DeleteTaskUseCase
import marcel.uni.gamifiedplanner.domain.usecases.task.GetTasksUseCase
import marcel.uni.gamifiedplanner.domain.usecases.task.UpdateTaskUseCase

class HomeViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    fun CreateTask(
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
        onResult: (CreateTaskResult) -> Unit
    ) {
        viewModelScope.launch {
            val result = createTaskUseCase.invoke(
                title,
                description,
                priority,
                status
            )
            onResult(result)
        }
    }

    fun UpdateTasks(
        taskId: String,
        taskName: String,
        taskDescription: String,
        taskPriority: Priority,
        taskStatus: TaskStatus
    ) {
        viewModelScope.launch {
            updateTaskUseCase.invoke(taskId, taskName, taskDescription, taskPriority, taskStatus)
        }
    }


    fun DeleteTask(taskId: String) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(taskId)
        }
    }

    val tasks: StateFlow<List<Task>> = getTasksUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList() // The initial List<Task>
        )}


