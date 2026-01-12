package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    createTask: (title: String, priority: Priority, description: String?, taskDuration: Int?, taskStartTime: Long?) -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    var taskTitle by remember { mutableStateOf("") }

    var taskDescription by remember { mutableStateOf("") }

    var taskPriority by remember { mutableStateOf(Priority.entries.first()) }

    var taskDurationString by remember { mutableStateOf("") }

    if (!show) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                if (show) onDismiss()
            },
    ) {}

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.padding(20.dp), shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 10.dp)
            ) {

                TextField(
                    modifier = Modifier.padding(10.dp),
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text("Title") })

                Spacer(modifier = Modifier.padding(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DropDownSelector(
                        Priority.entries.map { it.name },
                        taskPriority.name,
                        onSelect = { item -> taskPriority = Priority.valueOf(item) })

                    TextField(
                        modifier = Modifier.padding(10.dp),
                        value = taskDurationString,
                        onValueChange = { it ->
                            taskDurationString = it.filter { it.isDigit() }
                        },
                        label = { Text("Duration") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))

                TimePicker(
                    state = timePickerState
                )

                HorizontalDivider(modifier = Modifier.padding(5.dp))

                TextField(
                    modifier = Modifier.padding(10.dp),
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = { Text("Description") })

                Spacer(modifier = Modifier.padding(5.dp))


                Button(onClick = {
                    val duration = taskDurationString.toIntOrNull()

                    val zone = ZoneId.systemDefault()

                    val now = ZonedDateTime.now(zone)

                    val selected = now.withHour(timePickerState.hour)
                        .withMinute(timePickerState.minute)
                        .withSecond(0)
                        .withNano(0)

                    val taskStartMillis = selected.toInstant().toEpochMilli()

                    createTask(
                        taskTitle,
                        taskPriority,
                     taskDescription,
                        duration,
                        taskStartMillis,
                    )

                }) {
                    Text("Create Task")
                }
            }
        }
    }

}
