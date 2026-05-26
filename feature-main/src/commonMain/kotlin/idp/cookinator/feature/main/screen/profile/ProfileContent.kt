package idp.cookinator.feature.main.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.vector.Filter
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.main.screen.profile.contract.ProfileAction

@Composable
internal fun ProfileContent(
    onAction: (ProfileAction) -> Unit = {},
) {
    Column {
        AppTopBar(
            title = "My profile",
            trailingIcon = Icons.Filter,
            onTrailingAction = { onAction(ProfileAction.GoToSettings) },
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Center,
        ) {
            Text(text = "Profile Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = AppTheme {
    ProfileContent()
}