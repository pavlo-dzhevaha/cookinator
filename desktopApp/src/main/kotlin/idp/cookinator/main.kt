package idp.cookinator

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.app_name
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.getStartWithColor
import idp.cookinator.host.App
import idp.cookinator.host.di.initKoin
import org.jetbrains.compose.resources.stringResource
import java.awt.Dimension

fun main() {
    initKoin()

    application {
        AppTheme {
            Window(
                onCloseRequest = ::exitApplication,
                state = rememberWindowState(
                    position = WindowPosition.Aligned(Alignment.Center),
                    size = DpSize(1024.dp, 768.dp)
                ),
                title = stringResource(Res.string.app_name),
                icon = rememberVectorPainter(Icons.getStartWithColor(Theme.color.primary.p100)),
            ) {
                window.minimumSize = Dimension(800, 600)

                App()
            }
        }
    }
}