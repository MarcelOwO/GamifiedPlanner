package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import marcel.uni.gamifiedplanner.domain.models.Priority
import marcel.uni.gamifiedplanner.domain.models.TaskStatus
import marcel.uni.gamifiedplanner.domain.usecases.task.CreateTaskResult
import marcel.uni.gamifiedplanner.ui.home.HomeViewModel

@Composable
fun AddTaskDialog(
    show: Boolean, onDismiss: () -> Unit, vm: HomeViewModel
) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskPriority by remember { mutableStateOf(Priority.entries.first()) }
    var taskStatus by remember { mutableStateOf(TaskStatus.entries.first()) }

    if (!show) {
        return
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .blur(
                20.dp
            )
            .background(
                Color.Black.copy(alpha = 0.2f)
            )
            .clickable {
                if (show) {
                    onDismiss()
                }
            },
    ){}

    Surface(
        modifier = Modifier.padding(10.dp), shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {

            TextField(
                value = taskTitle,
                onValueChange = { taskTitle = it },
                label = { Text("Title") })
            Spacer(modifier = Modifier.padding(5.dp))

            TextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = { Text("Description") })
            Spacer(modifier = Modifier.padding(5.dp))
            DropDownSelector(
                Priority.entries.map { it.name },
                taskPriority.name,
                onSelect = { item -> taskPriority = Priority.valueOf(item) })
            Spacer(modifier = Modifier.padding(5.dp))
            DropDownSelector(
                TaskStatus.entries.map { it.name },
                taskStatus.name,
                onSelect = { item -> taskStatus = TaskStatus.valueOf(item) })
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = {
                vm.CreateTask(
                    taskTitle,
                    taskDescription,
                    taskPriority,
                    taskStatus,
                    onResult = { result ->

                        when (result) {
                            is CreateTaskResult.Success -> {
                                onDismiss()
                            }

                            is CreateTaskResult.Failure -> {

                            }

                            is CreateTaskResult.ValidationError -> {

                            }

                        }
                    })

            }) {
                Text("Create Task")
            }
        }


    }

}