package idp.cookinator.feature.createrecipe.screen.create

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.create_recipe_cook_time
import cookinator.localisation.generated.resources.create_recipe_edit_title
import cookinator.localisation.generated.resources.create_recipe_serves
import cookinator.localisation.generated.resources.create_recipe_title
import cookinator.localisation.generated.resources.home_trending_item_time
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.vector.ArrowLeft
import idp.cookinator.coreui.vector.Clock
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Star
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipeBottomBar
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipeCategorySection
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipeDietToggles
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipeExitDialog
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipeImageSourceSheet
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipeIngredientsSection
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipeMetaCard
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipePhotoSection
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipePickerSheet
import idp.cookinator.feature.createrecipe.screen.create.components.CreateRecipeTitleField
import idp.cookinator.feature.createrecipe.screen.create.contract.CreateRecipeIntent
import idp.cookinator.feature.createrecipe.screen.create.contract.CreateRecipeState
import idp.cookinator.feature.createrecipe.screen.create.contract.PickerType
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CreateRecipeContent(
    state: CreateRecipeState,
    onIntent: (CreateRecipeIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenTitle = if (state.isEditMode) {
        stringResource(Res.string.create_recipe_edit_title)
    } else {
        stringResource(Res.string.create_recipe_title)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = screenTitle,
                leadingIcon = Icons.ArrowLeft,
                onLeadingAction = { onIntent(CreateRecipeIntent.OnBackPressed) },
                modifier = Modifier.statusBarsPadding(),
            )
        },
        bottomBar = {
            CreateRecipeBottomBar(
                enabled = state.isSaveEnabled,
                loading = state.isSaving,
                onSave = { onIntent(CreateRecipeIntent.OnSave) },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding() + Theme.size.s8,
            ),
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                SpacerHeight(Theme.size.s12)
                CreateRecipePhotoSection(
                    imagePath = state.imagePath,
                    onClick = { onIntent(CreateRecipeIntent.OnPhotoClick) },
                )
            }
            item {
                SpacerHeight(Theme.size.s16)
                CreateRecipeTitleField(
                    value = state.title,
                    onValueChange = { onIntent(CreateRecipeIntent.OnTitleChange(it)) },
                )
            }
            item {
                SpacerHeight(Theme.size.s16)
                CreateRecipeMetaCard(
                    icon = Icons.Star,
                    label = stringResource(Res.string.create_recipe_serves),
                    value = formatServings(state.servings),
                    onClick = { onIntent(CreateRecipeIntent.OnOpenPicker(PickerType.Servings)) },
                )
            }
            item {
                SpacerHeight(Theme.size.s12)
                CreateRecipeMetaCard(
                    icon = Icons.Clock,
                    label = stringResource(Res.string.create_recipe_cook_time),
                    value = stringResource(
                        Res.string.home_trending_item_time,
                        state.readyInMinutes.toString()
                    ),
                    onClick = { onIntent(CreateRecipeIntent.OnOpenPicker(PickerType.CookTime)) },
                )
            }
            item {
                SpacerHeight(Theme.size.s24)
                CreateRecipeDietToggles(
                    vegetarian = state.vegetarian,
                    vegan = state.vegan,
                    glutenFree = state.glutenFree,
                    dairyFree = state.dairyFree,
                    onToggle = { onIntent(CreateRecipeIntent.OnDietToggle(it)) },
                )
            }
            item {
                SpacerHeight(Theme.size.s24)
                CreateRecipeCategorySection(
                    availableCategories = state.availableCategories,
                    selectedCategories = state.selectedCategories,
                    customCategoryInput = state.customCategoryInput,
                    onCategoryToggle = { onIntent(CreateRecipeIntent.OnCategoryToggle(it)) },
                    onCustomInputChange = {
                        onIntent(
                            CreateRecipeIntent.OnCustomCategoryInputChange(
                                it
                            )
                        )
                    },
                    onAddCustomCategory = { onIntent(CreateRecipeIntent.OnAddCustomCategory) },
                )
            }
            item {
                SpacerHeight(Theme.size.s24)
                CreateRecipeIngredientsSection(
                    ingredients = state.ingredients,
                    onNameChange = { id, value ->
                        onIntent(
                            CreateRecipeIntent.OnIngredientNameChange(
                                id,
                                value
                            )
                        )
                    },
                    onDescriptionChange = { id, value ->
                        onIntent(CreateRecipeIntent.OnIngredientDescriptionChange(id, value))
                    },
                    onRemove = { onIntent(CreateRecipeIntent.OnRemoveIngredient(it)) },
                    onAddAfter = { onIntent(CreateRecipeIntent.OnAddIngredientAfter(it)) },
                )
                Spacer(modifier = Modifier.height(Theme.size.s24))
            }
        }
    }

    if (state.showExitDialog) {
        CreateRecipeExitDialog(
            isEditMode = state.isEditMode,
            onDismiss = { onIntent(CreateRecipeIntent.OnDismissExitDialog) },
            onDiscard = { onIntent(CreateRecipeIntent.OnExitDiscard) },
            onSaveDraft = { onIntent(CreateRecipeIntent.OnExitSaveDraft) },
        )
    }

    if (state.showImageSourceSheet) {
        CreateRecipeImageSourceSheet(
            onDismiss = { onIntent(CreateRecipeIntent.OnDismissImageSourceSheet) },
            onSourceSelected = { onIntent(CreateRecipeIntent.OnImageSourceSelected(it)) },
        )
    }

    when (state.activePicker) {
        PickerType.Servings -> CreateRecipePickerSheet(
            pickerType = PickerType.Servings,
            title = stringResource(Res.string.create_recipe_serves),
            options = CreateRecipeOptions.servingOptions,
            selected = state.servings,
            formatOption = ::formatServings,
            onDismiss = { onIntent(CreateRecipeIntent.OnDismissPicker) },
            onSelected = { onIntent(CreateRecipeIntent.OnServingsSelected(it)) },
        )

        PickerType.CookTime -> CreateRecipePickerSheet(
            pickerType = PickerType.CookTime,
            title = stringResource(Res.string.create_recipe_cook_time),
            options = CreateRecipeOptions.cookTimeOptions,
            selected = state.readyInMinutes,
            formatOption = { minutes -> "${minutes} min" },
            onDismiss = { onIntent(CreateRecipeIntent.OnDismissPicker) },
            onSelected = { onIntent(CreateRecipeIntent.OnCookTimeSelected(it)) },
        )

        null -> Unit
    }
}

private fun formatServings(servings: Int): String =
    if (servings >= CreateRecipeOptions.SERVINGS_12_PLUS_VALUE) {
        "12+"
    } else {
        servings.toString().padStart(2, '0')
    }
