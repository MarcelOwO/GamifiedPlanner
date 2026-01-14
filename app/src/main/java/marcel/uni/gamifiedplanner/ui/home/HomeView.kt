package marcel.uni.gamifiedplanner.ui.home

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.ui.components.AddTaskDialog
import marcel.uni.gamifiedplanner.ui.components.TaskCard
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import marcel.uni.gamifiedplanner.R
import marcel.uni.gamifiedplanner.domain.logger.AppLogger
import marcel.uni.gamifiedplanner.ui.components.CustomSelect
import org.koin.compose.koinInject

@Composable
fun HomeView(
    vm: HomeViewModel = koinViewModel(),
    logger: AppLogger = koinInject()
) {
    var showMenu by remember { mutableStateOf(false) }

    val tasks: List<Task> by vm.tasks.collectAsStateWithLifecycle()

    var selectedOption by remember { mutableStateOf("Today") }
    val filteredTasks = remember(tasks, selectedOption) {
        if (selectedOption == "Today") {
            tasks.filter {
                DateUtils.isToday(it.startTime?.toDate()?.time ?: 0)
            }
        } else {
            tasks
        }
    }



    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(5.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Text(
                "Tasks",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(10.dp)
            )

            IconButton(
                onClick = {
                    logger.i("Adding task button pressed")
                    showMenu = true
                },
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_add_24),
                        contentDescription = "Add Task",
                    )
                }
            }
        }


        CustomSelect(options = listOf("Today", "All"), selectedOption, onSelect = { selected ->
            logger.i("Task filter selected option: $selected")
            selectedOption = selected
        }
        )

        Surface(
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(10.dp)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                items (filteredTasks){ task ->
                TaskCard(
                    task,
                    editTask = { task ->
                        logger.i("Editing task: ${task.title} ${task.id} ${task.description} ${task.priority} ${task.status} ${task.duration} ${task.startTime} ")
                        vm.UpdateTasks(
                            task.id,
                            task.title,
                            task.description ?: "",
                            task.priority,
                            task.status
                        )
                    },
                    deleteTask = { task ->
                        logger.i("Deleting task: ${task.title}")
                        vm.DeleteTask(task.id)
                    }
                )

                Spacer(modifier = Modifier.padding(5.dp))
            }
            }
        }
    }

    AddTaskDialog(
        showMenu,
        onDismiss = { showMenu = false },
        createTask = { title, priority, description, taskDuration, taskStartTime ->
            logger.i("Creating task")
            vm.CreateTask(
                title,
                priority,
                taskDuration,
                description,
                taskStartTime,
                onResult = { result ->
                    {
                        if (result.isSuccess) {
                            showMenu = false
                        } else {
                            logger.e("Error creating task")
                        }

                    }
                })
        }
    )


}
