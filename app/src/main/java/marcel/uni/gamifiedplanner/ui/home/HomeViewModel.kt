package marcel.uni.gamifiedplanner.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus
import marcel.uni.gamifiedplanner.domain.task.usecase.CreateTaskUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.DeleteTaskUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.GetTasksUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.UpdateTaskUseCase
import marcel.uni.gamifiedplanner.util.PlannerResult

class HomeViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val completeTaskUseCase: CompleteTaskUseCase
) : ViewModel() {

    fun CreateTask(
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
        onResult: (PlannerResult<Unit>) -> Unit
    ) {
        viewModelScope.launch {
            val result = createTaskUseCase(
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
            updateTaskUseCase(taskId, taskName, taskDescription, taskPriority, taskStatus)
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
            initialValue = emptyList()
        )

    fun CompleteTask(taskId:String){
        viewModelScope.launch{
            completeTaskUseCase(taskId)
        }
    }
}
