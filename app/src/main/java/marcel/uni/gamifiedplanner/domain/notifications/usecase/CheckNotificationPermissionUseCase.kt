package marcel.uni.gamifiedplanner.domain.notifications.usecase

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class CheckNotificationPermissionUseCase(
    private val context: Context
) {
    operator fun invoke(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }
}