package marcel.uni.gamifiedplanner.ui.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileView(
    vm: ProfileViewModel = koinViewModel()
) {
    val vmUserName by vm.username.collectAsStateWithLifecycle("")
    val vmEmail by vm.email.collectAsStateWithLifecycle("")

    val totalXp by vm.totalXp.collectAsStateWithLifecycle(0)
    val balance by vm.totalCurrency.collectAsStateWithLifecycle(0)

    var userName by remember { mutableStateOf(vmUserName) }
    var email by remember { mutableStateOf(vmEmail) }

    LaunchedEffect(vmUserName) {
        userName = vmUserName
    }

    LaunchedEffect(vmEmail) {
        email = vmEmail
    }

    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            "Profile",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(), shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {


                TextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = {
                    val trimmedUsername = userName.trim()
                    val trimmedEmail = email.trim()

                    if (trimmedEmail != vmEmail) {
                        vm.UpdateEmail(trimmedEmail)
                    }

                    if (trimmedUsername != vmUserName) {
                        vm.UpdateUsername(trimmedUsername)
                    }

                }) {
                    Text("Update Profile")
                }
                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text("Total XP: $totalXp xp")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Balance : $balance Coins")
            }
        }
    }
}
