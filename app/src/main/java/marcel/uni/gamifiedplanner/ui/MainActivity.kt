package marcel.uni.gamifiedplanner.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import marcel.uni.gamifiedplanner.ui.theme.GamifiedPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamifiedPlannerTheme {
                AppRoot()
            }
        }
    }

}