package idp.cookinator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.mmk.kmpnotifier.KMPNotifier
import com.mmk.kmpnotifier.local.LocalNotifications
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import idp.cookinator.host.App
import idp.cookinator.host.handleDeepLink
import idp.cookinator.host.registerNotificationClickHandler

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        KMPNotifier.initialize(
            configuration = NotificationPlatformConfiguration.Android(
                notificationIconResId = R.mipmap.ic_launcher_round,
                showPushNotification = true,
            ),
            LocalNotifications,
        )
        registerNotificationClickHandler()
        handleIntent(intent)

        setContent {
            LaunchedEffect(Unit) {
                KMPNotifier.permissionUtil.askNotificationPermission()
            }

            App()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.data?.toString()?.let(::handleDeepLink)
    }
}
