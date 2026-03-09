package idp.cookinator.host

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import idp.cookinator.coreui.styling.Theme
import idp.cookinator.coreui.styling.theme.AppTheme

@Composable
fun App() {
    // TODO setup dark mode toggle
    AppTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.neutral.white),
        ) {
            Text(
                "Hello, Cookinator! Running on ${getPlatform().name}."
            )
        }
    }
}

@Preview
@Composable
private fun Preview() = AppTheme {
    App()
}
