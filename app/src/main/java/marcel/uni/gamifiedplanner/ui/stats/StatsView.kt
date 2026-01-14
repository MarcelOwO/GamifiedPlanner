package marcel.uni.gamifiedplanner.ui.stats

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import marcel.uni.gamifiedplanner.ui.components.CustomSelect
import marcel.uni.gamifiedplanner.ui.components.TaskHistoryCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatsView(
    vm: StatsViewModel = koinViewModel()
) {
    var selectedTaskFilter by remember { mutableStateOf("Today") }

    var selectedCategory by remember { mutableStateOf("Tasks") }

    val tasks by vm.tasks.collectAsStateWithLifecycle()

    val filteredTasks = remember(tasks, selectedTaskFilter) {
        if (selectedTaskFilter == "Today") {
            tasks.filter { DateUtils.isToday(it.completedAt?.toDate()?.time ?: 0) }
        } else {
            tasks
        }
    }

    val userAchievements by vm.achievements.collectAsStateWithLifecycle()

    Column() {
        Text(
            "Stats",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp)
        )

        CustomSelect(listOf("Tasks", "Achievements"), selected = selectedCategory, onSelect = {
            selectedCategory = it
        })

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column() {
                if (selectedCategory == "Tasks") {

                    Text("Tasks")

                    CustomSelect(listOf("Today", "All"), selected = selectedTaskFilter, onSelect = {
                        selectedTaskFilter = it
                    })

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn() {
                        items(filteredTasks) { task ->
                            TaskHistoryCard(task)
                        }
                    }


                } else if (selectedCategory == "Achievements") {

                    Text("Achievements")

                    LazyColumn() {
                        items(userAchievements) { achievements ->
                            Text(
                                text = achievements.uuid,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }

            }

        }


    }

}