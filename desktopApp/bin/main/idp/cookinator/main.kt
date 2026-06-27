package idp.cookinator

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.mmk.kmpnotifier.KMPNotifier
import com.mmk.kmpnotifier.local.LocalNotifications
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import cookinator.core_ui.generated.resources.Res
import cookinator.core_ui.generated.resources.Res.drawable
import cookinator.core_ui.generated.resources.ic_launcher_playstore
import cookinator.localisation.generated.resources.Res.string
import cookinator.localisation.generated.resources.app_name
import idp.cookinator.host.App
import idp.cookinator.host.di.initKoin
import idp.cookinator.host.registerNotificationClickHandler
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import java.awt.Dimension
import java.io.File

fun main() {
    initKoin()

    val iconTempFile = File.createTempFile("notification_icon", ".png").apply {
        deleteOnExit()
    }

    runBlocking {
        val imageBytes = Res.readBytes("drawable/ic_launcher-playstore.png")
        iconTempFile.writeBytes(imageBytes)
    }

    KMPNotifier.initialize(
        NotificationPlatformConfiguration.Desktop(
            showPushNotification = true,
            notificationIconPath = iconTempFile.absolutePath,
        ),
        LocalNotifications,
    )

    registerNotificationClickHandler()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = rememberWindowState(
                position = WindowPosition.Aligned(Alignment.Center),
                size = DpSize(1024.dp, 768.dp),
            ),
            title = stringResource(string.app_name),
            icon = painterResource(drawable.ic_launcher_playstore),
        ) {
            window.minimumSize = Dimension(640, 480)

            App()
        }
    }
}
