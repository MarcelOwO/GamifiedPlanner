package marcel.uni.gamifiedplanner.ui.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import marcel.uni.gamifiedplanner.ui.auth.AuthViewModel
import org.koin.androidx.compose.koinViewModel
import marcel.uni.gamifiedplanner.R
@Preview
@Composable
fun AppHeader(vm: AppHeaderViewModel = koinViewModel(), authVm: AuthViewModel = koinViewModel()) {

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(5.dp)) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Gamified Planner")
            Spacer(modifier = Modifier.padding(10.dp))

            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    painter = painterResource(R.drawable.outline_person_24),
                    contentDescription = "User Menu"
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                Button(onClick = { authVm.logout() }) {
                    Text("Logout")
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                    .fillMaxHeight(0.07f)
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
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                    .fillMaxHeight(0.07f),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text("Streak 0")
                }
            }
        }

    }
}

