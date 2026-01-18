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
import marcel.uni.gamifiedplanner.di.LoggingModule
import marcel.uni.gamifiedplanner.di.ShopModule
import marcel.uni.gamifiedplanner.di.TaskModule
import marcel.uni.gamifiedplanner.di.UserModule
import marcel.uni.gamifiedplanner.ui.RootView
import marcel.uni.gamifiedplanner.ui.theme.ThemeHost
import marcel.uni.gamifiedplanner.ui.theme.ThemeViewModel
import marcel.uni.gamifiedplanner.util.RequestNotificationPermission
import marcel.uni.gamifiedplanner.util.createNotificationChannel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.compose.KoinApplication
import timber.log.Timber

// Entry point
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "[OwO]_$tag", message, t)
            }
        })

        enableEdgeToEdge()
        setContent {
            App()
        }
    }

    @Composable
    fun App() {
        KoinApplication(
            application = {
                modules(
                    LoggingModule().loggingModule,
                    AppModule().appModule,
                    FirebaseModule().firebaseModule,
                    TaskModule().taskModule,
                    AuthModule().authModule,
                    AchievementModule().achievementModule,
                    ShopModule().shopModule,
                    UserModule().userModule,
                )
            }) {
            createNotificationChannel(context = this@MainActivity)

            RequestNotificationPermission { isGranted ->
                if (isGranted) {
                    Timber.i("Notification permission granted")
                } else {
                    Timber.i("Notification permission denied")
                }
            }

            val themeViewModel: ThemeViewModel = getViewModel()

            ThemeHost(vm = themeViewModel) {
                RootView()
            }
        }
    }


}