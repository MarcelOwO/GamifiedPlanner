package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun TaskCard(task: Task, vm: HomeViewModel) {

    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
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
                        vm.DeleteTask(task.id)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_delete_24),
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
                    collection = Priority.entries.map { it -> it.name },
                    task.priority.name,
                    onSelect = {})
                DropDownSelector(
                    collection = TaskStatus.entries.map { it -> it.name },
                    task.status.name,
                    onSelect = {})
            }
            Text(text = task.description ?: "")
        }
    }
}