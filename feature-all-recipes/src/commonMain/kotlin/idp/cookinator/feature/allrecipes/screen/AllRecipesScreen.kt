package idp.cookinator.feature.allrecipes.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_empty_info
import cookinator.localisation.generated.resources.home_error_info
import idp.cookinator.coreui.component.info.InfoContainer
import idp.cookinator.coreui.viewmodel.components.MviStateProvider
import idp.cookinator.feature.allrecipes.screen.contract.AllRecipesEvent
import idp.cookinator.feature.allrecipes.screen.contract.AllRecipesIntent
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigate
import idp.cookinator.feature.navigation.extension.navigateUp
import idp.cookinator.feature.navigation.features.HomeRecipeSection
import idp.cookinator.feature.navigation.features.NavigationRecipe
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AllRecipesScreen(
    section: HomeRecipeSection,
    navigator: Navigator,
    viewModel: AllRecipesViewModel = koinViewModel(),
) {
    MviStateProvider(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                is AllRecipesEvent.NavigateToRecipe -> {
                    navigator.navigate(NavigationRecipe.Detail(event.recipeId))
                }
                AllRecipesEvent.NavigateBack -> navigator.navigateUp()
            }
        },
    ) { state ->
        state.uiState.Render(
            loading = {
                CircularProgressIndicator()
            },
            empty = {
                InfoContainer(
                    title = stringResource(Res.string.home_empty_info),
                    onAction = { viewModel.onIntent(AllRecipesIntent.OnRetry) },
                )
            },
            error = {
                InfoContainer(
                    title = stringResource(Res.string.home_error_info),
                    onAction = { viewModel.onIntent(AllRecipesIntent.OnRetry) },
                )
            },
            success = {
                AllRecipesContent(
                    section = section,
                    state = state,
                    onIntent = viewModel::onIntent,
                )
            },
            nonSuccessModifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
        )
    }
}
