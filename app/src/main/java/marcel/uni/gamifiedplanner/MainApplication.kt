package marcel.uni.gamifiedplanner

import android.app.Application
import androidx.work.Configuration
import marcel.uni.gamifiedplanner.di.AchievementModule
import marcel.uni.gamifiedplanner.di.AppModule
import marcel.uni.gamifiedplanner.di.AuthModule
import marcel.uni.gamifiedplanner.di.FirebaseModule
import marcel.uni.gamifiedplanner.di.LoggingModule
import marcel.uni.gamifiedplanner.di.ShopModule
import marcel.uni.gamifiedplanner.di.TaskModule
import marcel.uni.gamifiedplanner.di.UserModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.android.ext.android.get
import org.koin.core.context.GlobalContext.startKoin

// Application Entry point
class MainApplication() : Application(), Configuration.Provider {
    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            workManagerFactory()
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
        }

    }

    // for getting notifications to work
    override val workManagerConfiguration: Configuration
        get()= Configuration.Builder()
            .setWorkerFactory(get())
            .build()

}