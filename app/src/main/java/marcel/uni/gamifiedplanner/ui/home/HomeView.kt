package marcel.uni.gamifiedplanner.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import marcel.uni.gamifiedplanner.R
import marcel.uni.gamifiedplanner.ui.components.CustomSelect

@Composable
fun HomeView(
    vm: HomeViewModel = koinViewModel()
) {
    var showMenu by remember { mutableStateOf(false) }

    val tasks: List<Task> by vm.tasks.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(5.dp)
    ) {
        IconButton(
            onClick = { showMenu = true },
        ) {
            Surface(modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.primary,
                ){
                Icon(
                    painter = painterResource(R.drawable.outline_add_24),
                    contentDescription = "Add Task",
                )
            }

        }

        var selectedOption by remember { mutableStateOf("Today") }
        CustomSelect(options = listOf("Today", "All"), selectedOption, onSelect = { selected ->
            selectedOption = selected
        }
        )


        Surface(
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(20.dp)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                items(tasks) { task ->
                    TaskCard(task,vm)
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }
        }
    }

    AddTaskDialog(
        showMenu, onDismiss = { showMenu = false }, vm
    )


}
