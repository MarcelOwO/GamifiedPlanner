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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import marcel.uni.gamifiedplanner.ui.components.AchievementCard
import marcel.uni.gamifiedplanner.ui.components.CustomSelect
import marcel.uni.gamifiedplanner.ui.components.TaskHistoryCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatsView(
    vm: StatsViewModel = koinViewModel()
) {
    var selectedTaskFilter by remember { mutableStateOf("Today") }

    var selectedTaskCategory by remember { mutableStateOf("Tasks") }

    val tasks by vm.tasks.collectAsStateWithLifecycle()

    val filteredTasks = remember(tasks, selectedTaskFilter) {
        if (selectedTaskFilter == "Today") {
            tasks.filter { DateUtils.isToday(it.completedAt.toDate().time) }
        } else {
            tasks
        }
    }

    val achievements by vm.achievements.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            "Stats",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp)
        )

        CustomSelect(listOf("Tasks", "Achievements"), selected = selectedTaskCategory, onSelect = {
            if (it == "Achievements") {

                vm.fetchAchievements()
            }
            selectedTaskCategory = it
        })

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                if (selectedTaskCategory == "Tasks") {

                    Text("Completed Tasks")

                    CustomSelect(listOf("Today", "All"), selected = selectedTaskFilter, onSelect = {
                        selectedTaskFilter = it
                    })

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn {
                        items(filteredTasks) { task ->
                            TaskHistoryCard(task)
                        }
                    }


                } else if (selectedTaskCategory == "Achievements") {

                    Text("Achievements")

                    LazyColumn {
                        items(achievements) { achievement ->
                            AchievementCard(achievement)

                        }
                    }
                }
            }
        }
    }
}