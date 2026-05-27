package idp.cookinator.feature.main.screen.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.profile_title
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.vector.Filter
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.main.screen.profile.contract.ProfileAction
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ProfileContent(
    modifier: Modifier = Modifier,
    bottomBarHeight: Dp = Dp.Hairline,
    onAction: (ProfileAction) -> Unit = {},
) {
    LazyColumn(
        contentPadding = PaddingValues(
            bottom = bottomBarHeight + Theme.size.s16,
        ),
        modifier = modifier
            .fillMaxSize(),
    ) {
        stickyHeader {
            AppTopBar(
                title = stringResource(Res.string.profile_title),
                trailingIcon = Icons.Filter,
                onTrailingAction = { onAction(ProfileAction.GoToSettings) },
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    ProfileContent()
}