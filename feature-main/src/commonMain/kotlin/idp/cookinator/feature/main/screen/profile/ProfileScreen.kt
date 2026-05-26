package idp.cookinator.feature.main.screen.profile

import androidx.compose.runtime.Composable
import idp.cookinator.feature.main.screen.profile.contract.ProfileAction
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.features.NavigationSettings

@Composable
internal fun ProfileScreen(
    navigator: Navigator,
) {
    ProfileContent(
        onAction = { action ->
            when (action) {
                ProfileAction.GoToSettings -> navigator.add(NavigationSettings.Settings)
            }
        },
    )
}
