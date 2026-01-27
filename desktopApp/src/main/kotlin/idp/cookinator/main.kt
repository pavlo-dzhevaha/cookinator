package idp.cookinator

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        App()
    }
}