package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import marcel.uni.gamifiedplanner.R
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus
import marcel.uni.gamifiedplanner.util.toFormatedString

@Composable
fun TaskCard(
    task: Task,
    editTask: (task: Task) -> Unit,
    deleteTask: (task: Task) -> Unit,
    updateStatus: (task: Task) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {
                        deleteTask(task)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_delete_24),
                            contentDescription = "Delete Task"
                        )
                    }
                    IconButton(onClick = {
                        editTask(task)
                    })
                    {
                        Icon(
                            painter = painterResource(R.drawable.outline_edit_24),
                            contentDescription = "Edit Task"
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Priority:")
                    Text("   ${task.priority.name}")
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text("Status:")
                    DropDownSelector(
                        collection = TaskStatus.entries.map { it.name },
                        selected = task.status.name,
                        onSelect = { newStatus ->
                            updateStatus(task.copy(status = TaskStatus.valueOf(newStatus)))
                        })
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Start Time: ${task.startTime.toFormatedString()}")
                    Text("Duration: ${task.duration} mins")
                }
            }

            HorizontalDivider(modifier = Modifier.padding(5.dp))
            Text(text = task.description, modifier = Modifier.padding(5.dp))
        }
    }
}