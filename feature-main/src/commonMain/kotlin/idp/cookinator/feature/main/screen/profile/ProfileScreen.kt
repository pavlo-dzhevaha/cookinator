package idp.cookinator.feature.main.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import idp.cookinator.coreui.viewmodel.components.MviStateProvider
import idp.cookinator.feature.main.screen.profile.contract.ProfileAction
import idp.cookinator.feature.main.screen.profile.contract.ProfileEvent
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.features.NavigationCreateRecipe
import idp.cookinator.feature.navigation.features.NavigationRecipe
import idp.cookinator.feature.navigation.features.NavigationSettings
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun ProfileScreen(
    navigator: Navigator,
    bottomBarHeight: Dp,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    MviStateProvider(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                is ProfileEvent.NavigateToUserRecipe ->
                    navigator.add(NavigationRecipe.UserDetail(event.userRecipeId))
                is ProfileEvent.NavigateToEditUserRecipe ->
                    navigator.add(NavigationCreateRecipe.Edit(event.userRecipeId))
            }
        },
    ) { state ->
        ProfileContent(
            state = state,
            bottomBarHeight = bottomBarHeight,
            onIntent = viewModel::onIntent,
            onAction = { action ->
                when (action) {
                    ProfileAction.GoToSettings -> navigator.add(NavigationSettings.Settings)
                }
            },
        )
    }
}
