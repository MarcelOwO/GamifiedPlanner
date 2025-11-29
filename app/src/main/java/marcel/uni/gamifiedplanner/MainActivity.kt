package marcel.uni.gamifiedplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import marcel.uni.gamifiedplanner.di.AchievementModule
import marcel.uni.gamifiedplanner.di.AppModule
import marcel.uni.gamifiedplanner.di.AuthModule
import marcel.uni.gamifiedplanner.di.FirebaseModule
import marcel.uni.gamifiedplanner.di.ShopModule
import marcel.uni.gamifiedplanner.di.TaskModule
import marcel.uni.gamifiedplanner.di.UserModule
import marcel.uni.gamifiedplanner.ui.RootView
import marcel.uni.gamifiedplanner.ui.theme.GamifiedPlannerTheme
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamifiedPlannerTheme {
                App()
            }
        }
    }

    @Composable
    fun App() {
        KoinApplication(
            application = {
                modules(
                    AppModule().appModule,
                    FirebaseModule().firebaseModule,
                    TaskModule().taskModule,
                    AuthModule().authModule,
                    AchievementModule().achievementModule,
                    ShopModule().shopModule,
                    UserModule().userModule,
                )
            }
        ) {
            RootView()
        }
    }
}