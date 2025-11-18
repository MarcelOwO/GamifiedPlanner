package marcel.uni.gamifiedplanner

import android.app.Application
import marcel.uni.gamifiedplanner.di.AppModule
import marcel.uni.gamifiedplanner.di.FirebaseModule
import marcel.uni.gamifiedplanner.di.TaskModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

//Main entry into app
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                AppModule().appModule,
                FirebaseModule().firebaseModule,
                TaskModule().taskModule,
            )

        }
    }
}