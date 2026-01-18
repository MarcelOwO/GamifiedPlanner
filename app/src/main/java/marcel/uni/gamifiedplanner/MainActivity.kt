package marcel.uni.gamifiedplanner


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import marcel.uni.gamifiedplanner.ui.RootView
import marcel.uni.gamifiedplanner.ui.theme.ThemeHost
import marcel.uni.gamifiedplanner.ui.theme.ThemeViewModel
import marcel.uni.gamifiedplanner.util.RequestNotificationPermission
import marcel.uni.gamifiedplanner.util.createNotificationChannel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

// Ui Entry point
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