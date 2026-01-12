package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
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
import com.google.firebase.Timestamp
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import java.time.ZoneId
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    createTask: (title: String, priority: Priority, description: String?, taskDuration: Int?, taskStartTime: Timestamp?) -> Unit
) {
    if (!show) return

    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskPriority by remember { mutableStateOf(Priority.entries.first()) }
    var taskDurationString by remember { mutableStateOf("") }
    var showDateDialog by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Surface(

            modifier = Modifier
                .padding(20.dp)
                .clickable(enabled = false) {},
            shape = RoundedCornerShape(10.dp),
            tonalElevation = 3.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("New Task", modifier = Modifier.padding(bottom = 8.dp))

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text("Title") }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        DropDownSelector(
                            Priority.entries.map { it.name },
                            taskPriority.name,
                            onSelect = { item -> taskPriority = Priority.valueOf(item) }
                        )
                    }

                    TextField(
                        modifier = Modifier.weight(1f),
                        value = taskDurationString,
                        onValueChange = {
                            taskDurationString = it.filter { char -> char.isDigit() }
                        },
                        label = { Text("Mins") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TimeInput(state = timePickerState)

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(onClick = { showDateDialog = true }) {
                        Text("Set Date")
                    }
                }

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = { Text("Description (Optional)") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val duration = taskDurationString.toIntOrNull()
                        val zone = ZoneId.systemDefault()


                        val baseDateMillis =
                            datePickerState.selectedDateMillis ?: System.currentTimeMillis()

                        val taskStartInstant = java.time.Instant.ofEpochMilli(baseDateMillis)
                            .atZone(zone)
                            .withHour(timePickerState.hour)
                            .withMinute(timePickerState.minute)
                            .withSecond(0)
                            .withNano(0)
                            .toInstant()

                        val taskStartTimestamp =
                            Timestamp(taskStartInstant.epochSecond, taskStartInstant.nano)

                        createTask(
                            taskTitle,
                            taskPriority,
                            taskDescription.ifBlank { null },
                            duration,
                            taskStartTimestamp,
                        )
                        onDismiss()
                    }
                ) {
                    Text("Create Task")
                }
            }
        }
    }


    if (showDateDialog) {
        androidx.compose.material3.DatePickerDialog(
            onDismissRequest = { showDateDialog = false },
            confirmButton = {
                Button(onClick = { showDateDialog = false }) { Text("OK") }
            }
        ) {
            androidx.compose.material3.DatePicker(state = datePickerState)
        }
    }
}