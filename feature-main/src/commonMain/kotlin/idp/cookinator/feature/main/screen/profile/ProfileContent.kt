package idp.cookinator.feature.main.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.profile_my_recipes
import cookinator.localisation.generated.resources.profile_my_recipes_empty
import cookinator.localisation.generated.resources.profile_recipe_delete
import cookinator.localisation.generated.resources.profile_title
import cookinator.localisation.generated.resources.recipe_detail_edit
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.component.grid.AdaptiveLazyVerticalGrid
import idp.cookinator.coreui.component.info.InfoContainer
import idp.cookinator.coreui.component.recipecard.RecipeCard
import idp.cookinator.coreui.component.recipecard.RecipeCardMenuItem
import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.vector.Filter
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.main.screen.profile.components.ProfileDeleteRecipeDialog
import idp.cookinator.feature.main.screen.profile.contract.ProfileAction
import idp.cookinator.feature.main.screen.profile.contract.ProfileIntent
import idp.cookinator.feature.main.screen.profile.contract.ProfileRecipeItem
import idp.cookinator.feature.main.screen.profile.contract.ProfileState
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ProfileContent(
    modifier: Modifier = Modifier,
    state: ProfileState,
    bottomBarHeight: Dp = Dp.Hairline,
    onIntent: (ProfileIntent) -> Unit = {},
    onAction: (ProfileAction) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(Res.string.profile_title),
            trailingIcon = Icons.Filter,
            onTrailingAction = { onAction(ProfileAction.GoToSettings) },
        )
        Text(
            text = stringResource(Res.string.profile_my_recipes),
            style = Theme.typography.bold.h5,
            color = Theme.color.neutral.n100,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Theme.size.s20,
                    vertical = Theme.size.s12,
                ),
        )
        when (state.uiState) {
            UiState.EMPTY -> {
                Text(
                    text = stringResource(Res.string.profile_my_recipes_empty),
                    style = Theme.typography.regular.label,
                    color = Theme.color.neutral.n50,
                    modifier = Modifier
                        .padding(horizontal = Theme.size.s20)
                        .weight(1f),
                )
            }
            UiState.ERROR -> {
                InfoContainer(
                    title = stringResource(Res.string.profile_my_recipes_empty),
                    onAction = { onIntent(ProfileIntent.OnRetry) },
                    modifier = Modifier
                        .padding(horizontal = Theme.size.s20)
                        .weight(1f),
                )
            }
            else -> {
                AdaptiveLazyVerticalGrid(
                    bottomInset = bottomBarHeight,
                    modifier = Modifier.weight(1f),
                ) {
                    items(
                        items = state.items,
                        key = { it.userRecipeId },
                    ) { item ->
                        RecipeCard(
                            item = item.model,
                            imageHeight = Dp.Unspecified,
                            onClick = { onIntent(ProfileIntent.OnRecipeClick(item)) },
                            onLike = {},
                            optionsMenuItems = listOf(
                                RecipeCardMenuItem(
                                    label = stringResource(Res.string.recipe_detail_edit),
                                    onClick = { onIntent(ProfileIntent.OnEditRecipe(item)) },
                                ),
                                RecipeCardMenuItem(
                                    label = stringResource(Res.string.profile_recipe_delete),
                                    onClick = { onIntent(ProfileIntent.OnDeleteRecipe(item)) },
                                ),
                            ),
                            modifier = Modifier.animateItem(),
                        )
                    }
                }
            }
        }
    }

    state.deleteConfirmation?.let { item ->
        ProfileDeleteRecipeDialog(
            recipeTitle = item.model.recipe.title,
            onDismiss = { onIntent(ProfileIntent.OnDismissDeleteDialog) },
            onConfirm = { onIntent(ProfileIntent.OnConfirmDeleteRecipe) },
        )
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    ProfileContent(
        state = ProfileState.initialState.copy(
            items = ProfileRecipeItem.stubs,
        ),
        bottomBarHeight = Dp.Hairline,
        onIntent = {},
        onAction = {},
    )
}
