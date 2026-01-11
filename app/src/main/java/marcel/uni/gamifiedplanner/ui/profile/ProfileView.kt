package marcel.uni.gamifiedplanner.ui.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileView(
    vm: ProfileViewModel = koinViewModel()
) {
Column(){

    Text(
        "Profile",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(10.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))

    Surface(modifier = Modifier
        .padding(10.dp)
        .fillMaxSize(), shape = RoundedCornerShape(20.dp)) {

    }
}


}
