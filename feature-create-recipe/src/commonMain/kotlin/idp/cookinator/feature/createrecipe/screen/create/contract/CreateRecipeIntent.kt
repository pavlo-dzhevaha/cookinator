package idp.cookinator.feature.createrecipe.screen.create.contract

import idp.cookinator.coreui.viewmodel.base.BaseIntent
import idp.cookinator.feature.createrecipe.image.ImageSource

internal sealed interface CreateRecipeIntent : BaseIntent {
    data object OnBackPressed : CreateRecipeIntent
    data object OnDismissExitDialog : CreateRecipeIntent
    data object OnExitDiscard : CreateRecipeIntent
    data object OnExitSaveDraft : CreateRecipeIntent
    data object OnSave : CreateRecipeIntent
    data class OnTitleChange(val value: String) : CreateRecipeIntent
    data class OnDietToggle(val type: DietType) : CreateRecipeIntent
    data class OnCategoryToggle(val category: String) : CreateRecipeIntent
    data class OnCustomCategoryInputChange(val value: String) : CreateRecipeIntent
    data object OnAddCustomCategory : CreateRecipeIntent
    data class OnIngredientNameChange(val localId: String, val value: String) : CreateRecipeIntent
    data class OnIngredientDescriptionChange(val localId: String, val value: String) : CreateRecipeIntent
    data class OnRemoveIngredient(val localId: String) : CreateRecipeIntent
    data class OnAddIngredientAfter(val localId: String) : CreateRecipeIntent
    data object OnPhotoClick : CreateRecipeIntent
    data object OnDismissImageSourceSheet : CreateRecipeIntent
    data class OnImageSourceSelected(val source: ImageSource) : CreateRecipeIntent
    data class OnImagePicked(val path: String?) : CreateRecipeIntent
    data class OnOpenPicker(val type: PickerType) : CreateRecipeIntent
    data object OnDismissPicker : CreateRecipeIntent
    data class OnServingsSelected(val servings: Int) : CreateRecipeIntent
    data class OnCookTimeSelected(val minutes: Int) : CreateRecipeIntent
}
