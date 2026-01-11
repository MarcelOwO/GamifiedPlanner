package marcel.uni.gamifiedplanner.ui.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.collectAsState
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
    val level by vm.currentLevel.collectAsState()
    val xp by vm.currentXp.collectAsState()
    val progress by vm.xpProgress.collectAsState()

    Column(modifier = Modifier.padding(5.dp)) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Gamified Planner",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_person_24),
                            contentDescription = "User Menu",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    DropdownMenu(
                        shape= RoundedCornerShape(10.dp),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {

                        Button(modifier = Modifier.padding(10.dp),
                            onClick = { authVm.logout() }) {
                            Text("Logout")
                        }
                    }
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                    .fillMaxHeight(0.07f)
            ) {
                if (level == null || xp == null) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {

                        Text("Loading...",color  =  MaterialTheme.colorScheme.secondary)
                    }
                } else {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row {
                            Text("Level ${level}",color  =  MaterialTheme.colorScheme.secondary)
                            Text("XP ${xp}/100",color  =  MaterialTheme.colorScheme.secondary )
                        }
                    LinearProgressIndicator(progress = { progress.toFloat() / 100 })
                    }
                }


            }
            Surface(
                shape = RoundedCornerShape(10.dp),
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
                    Text("Streak 0",color  =  MaterialTheme.colorScheme.secondary)
                }
            }
        }

    }
}



