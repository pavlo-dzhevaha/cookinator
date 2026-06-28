package idp.cookinator.feature.createrecipe.screen.create.contract

import idp.cookinator.coreui.viewmodel.base.BaseState

internal enum class DietType {
    Vegetarian,
    Vegan,
    GlutenFree,
    DairyFree,
}

internal enum class PickerType {
    Servings,
    CookTime,
}

internal data class IngredientFormItem(
    val localId: String,
    val name: String = "",
    val description: String = "",
)

internal data class CreateRecipeState(
    val isEditMode: Boolean = false,
    val imagePath: String? = null,
    val title: String = "",
    val servings: Int = 1,
    val readyInMinutes: Int = 5,
    val vegetarian: Boolean = false,
    val vegan: Boolean = false,
    val glutenFree: Boolean = false,
    val dairyFree: Boolean = false,
    val selectedCategories: Set<String> = emptySet(),
    val customCategoryInput: String = "",
    val ingredients: List<IngredientFormItem> = listOf(IngredientFormItem(localId = "0")),
    val availableCategories: List<String> = emptyList(),
    val showExitDialog: Boolean = false,
    val showImageSourceSheet: Boolean = false,
    val activePicker: PickerType? = null,
    val isSaving: Boolean = false,
) : BaseState {
    val isSaveEnabled: Boolean
        get() = imagePath != null &&
            title.isNotBlank() &&
            selectedCategories.isNotEmpty() &&
            ingredients.any { it.name.isNotBlank() && it.description.isNotBlank() }

    companion object {
        val initialState = CreateRecipeState()
    }
}
