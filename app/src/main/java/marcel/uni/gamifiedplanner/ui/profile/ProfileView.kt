package marcel.uni.gamifiedplanner.ui.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import marcel.uni.gamifiedplanner.domain.task.model.Task
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileView(
    vm: ProfileViewModel = koinViewModel()
) {
    val userName: String by vm.username.collectAsStateWithLifecycle()

    Column() {

        Text(
            "Profile",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(), shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = userName)
            HorizontalDivider(modifier=Modifier.padding(10.dp))
        }
    }


}
