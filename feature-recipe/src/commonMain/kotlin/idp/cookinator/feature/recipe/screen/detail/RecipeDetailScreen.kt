package idp.cookinator.feature.recipe.screen.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.recipe_detail_error
import idp.cookinator.coreui.component.info.InfoContainer
import idp.cookinator.coreui.viewmodel.components.MviStateProvider
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigateUp
import idp.cookinator.feature.navigation.features.NavigationCreateRecipe
import idp.cookinator.feature.recipe.screen.detail.contract.RecipeDetailEvent
import idp.cookinator.feature.recipe.screen.detail.contract.RecipeDetailIntent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun RecipeDetailScreen(
    args: RecipeDetailArgs,
    navigator: Navigator,
    viewModel: RecipeDetailViewModel = koinViewModel { parametersOf(args) },
) {
    MviStateProvider(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                is RecipeDetailEvent.NavigateToEdit ->
                    navigator.add(NavigationCreateRecipe.Edit(event.userRecipeId))
            }
        },
    ) { state ->
        state.uiState.Render(
            loading = {
                CircularProgressIndicator()
            },
            error = {
                InfoContainer(
                    title = stringResource(Res.string.recipe_detail_error),
                    onAction = { viewModel.onIntent(RecipeDetailIntent.OnRetry) },
                )
            },
            empty = {
                InfoContainer(
                    title = stringResource(Res.string.recipe_detail_error),
                    onAction = { viewModel.onIntent(RecipeDetailIntent.OnRetry) },
                )
            },
            success = {
                RecipeDetailContent(
                    state = state,
                    onIntent = viewModel::onIntent,
                    onBack = navigator::navigateUp,
                )
            },
            nonSuccessModifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
        )
    }
}
