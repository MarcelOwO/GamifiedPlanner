package marcel.uni.gamifiedplanner.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus
import marcel.uni.gamifiedplanner.domain.task.usecase.CreateTaskUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.DeleteTaskUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.ObserveTasksUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.ObserveTodaysTasksUseCase
import marcel.uni.gamifiedplanner.domain.task.usecase.UpdateTaskUseCase
import marcel.uni.gamifiedplanner.util.PlannerResult

class HomeViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val observeTodaysTasksUseCase: ObserveTodaysTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val logger: AppLogger
) : ViewModel() {

    fun createTask(
        title: String,
        priority: Priority,
        duration: Int?,
        description: String?,
        startTime: Timestamp?,
        onResult: (PlannerResult<Unit>) -> Unit
    ) {
        viewModelScope.launch {
            logger.i("Creating task")
            val result = createTaskUseCase(
                title = title,
                priority = priority,
                description = description ?: "",
                status = TaskStatus.OPEN,
                duration = (duration ?: 0).toLong(),
                startTime = startTime ?: Timestamp.now()
            )
            onResult(result)
        }
    }

    fun updateTask(
        taskId: String,
        taskName: String?,
        taskDescription: String?,
        taskPriority: Priority?,
        taskStatus: TaskStatus?,
        taskDuration: Long?,
        taskStartTime: Timestamp?
    ) {
        viewModelScope.launch {
            logger.i("Updating task")
            updateTaskUseCase(
                taskId,
                taskName,
                taskDescription,
                taskPriority,
                taskStatus,
                taskDuration,
                taskStartTime,
            )
        }
    }


    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            logger.i("Deleting task {$taskId}")
            deleteTaskUseCase.invoke(taskId)
        }
    }

    val tasks: StateFlow<List<Task>> = observeTasksUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val todayTasks: StateFlow<List<Task>> = observeTodaysTasksUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
