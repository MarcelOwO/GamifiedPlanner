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
import marcel.uni.gamifiedplanner.domain.task.model.Priority
import marcel.uni.gamifiedplanner.domain.task.model.Task
import marcel.uni.gamifiedplanner.domain.task.model.TaskStatus
import marcel.uni.gamifiedplanner.ui.home.HomeViewModel

@Composable
fun TaskCard(task: Task, editTask: (task: Task) -> Unit, deleteTask: (task: Task) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(10.dp))
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
                DropDownSelector(
                    collection = Priority.entries.map { it.name },
                    selected = task.priority.name,
                    onSelect = { newPriority ->
                        editTask(task.copy(priority = Priority.valueOf(newPriority)))
                    })
                DropDownSelector(
                    collection = TaskStatus.entries.map { it.name },
                    selected = task.status.name,
                    onSelect = { newStatus ->
                        editTask(task.copy(status = TaskStatus.valueOf(newStatus)))
                    })
            }

            HorizontalDivider(modifier = Modifier.padding(10.dp))
            Text(text = task.description , modifier = Modifier.padding(10.dp))
        }
    }
}