package marcel.uni.gamifiedplanner.ui.components

import android.view.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppHeader() {
    Column(modifier = Modifier.padding(5.dp)) {
        Column {
            Text("Gamified Planner")
        }
        Row(modifier=Modifier.fillMaxWidth()) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.weight(1f).padding(5.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row {
                        Text("Level 1")
                        Text("XP 0/100")
                    }
                    LinearProgressIndicator()
                }
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.weight(1f).padding(5.dp)
            ) {
                Text("Streak 0")
            }
        }

    }
}

