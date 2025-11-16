package marcel.uni.gamifiedplanner.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeView(
    vm: HomeViewModel = viewModel()
) {
    val state by vm.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(5.dp)
    ) {
        Button(
            onClick = {},
        ) {
            Text("Create Task")
        }
        Surface(
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(5.dp),
            shape = RoundedCornerShape(20.dp),
        ) {

            Row() {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .fillMaxHeight(0.05f),
                    onClick = {},
                ) {
                    Text("Today")

                }

                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .fillMaxHeight(0.05f),
                    onClick = {},
                ) {
                    Text("All")
                }

            }

        }

        Surface(
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)

        ) {

        }
    }
}
