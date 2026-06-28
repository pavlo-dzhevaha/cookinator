package idp.cookinator.feature.createrecipe.screen.create

import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.ClearRecipeDraftUseCase
import idp.cookinator.domain.recipe.CreateUserRecipeUseCase
import idp.cookinator.domain.recipe.GetRecipeByIdUseCase
import idp.cookinator.domain.recipe.GetUserRecipeByIdUseCase
import idp.cookinator.domain.recipe.ObserveRecipeDishTypesUseCase
import idp.cookinator.domain.recipe.ObserveRecipeDraftUseCase
import idp.cookinator.domain.recipe.SaveRecipeDraftUseCase
import idp.cookinator.domain.recipe.UpdateUserRecipeUseCase
import idp.cookinator.feature.createrecipe.image.ImageSource
import idp.cookinator.feature.createrecipe.screen.create.contract.CreateRecipeEvent
import idp.cookinator.feature.createrecipe.screen.create.contract.CreateRecipeIntent
import idp.cookinator.feature.createrecipe.screen.create.contract.CreateRecipeState
import idp.cookinator.feature.createrecipe.screen.create.contract.DietType
import idp.cookinator.feature.createrecipe.screen.create.contract.IngredientFormItem
import idp.cookinator.feature.createrecipe.screen.create.contract.PickerType
import idp.cookinator.model.Ingredient
import idp.cookinator.model.Recipe
import idp.cookinator.model.RecipeDraft
import idp.cookinator.model.UserRecipe
import idp.cookinator.model.RecipeDraftIngredient
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlin.random.Random

internal class CreateRecipeViewModel(
    private val mode: CreateRecipeMode,
    private val observeRecipeDishTypes: ObserveRecipeDishTypesUseCase,
    private val observeRecipeDraft: ObserveRecipeDraftUseCase,
    private val saveRecipeDraft: SaveRecipeDraftUseCase,
    private val clearRecipeDraft: ClearRecipeDraftUseCase,
    private val createUserRecipe: CreateUserRecipeUseCase,
    private val updateUserRecipe: UpdateUserRecipeUseCase,
    private val getUserRecipeById: GetUserRecipeByIdUseCase,
    private val getRecipeById: GetRecipeByIdUseCase,
) : MviViewModel<CreateRecipeState, CreateRecipeIntent, CreateRecipeEvent>(CreateRecipeState.initialState) {

    private var baselineSnapshot: CreateRecipeState = CreateRecipeState.initialState
    private var observeCategoriesJob: Job? = null
    private var nextIngredientId = 1

    init {
        observeCategories()
        when (mode) {
            CreateRecipeMode.Create -> loadDraft()
            is CreateRecipeMode.Edit -> loadForEdit(mode.userRecipeId)
            is CreateRecipeMode.FromRecipe -> loadFromRecipe(mode.recipeId)
        }
    }

    override fun onIntent(intent: CreateRecipeIntent) {
        when (intent) {
            CreateRecipeIntent.OnBackPressed -> onBackPressed()
            CreateRecipeIntent.OnDismissExitDialog -> updateState { it.copy(showExitDialog = false) }
            CreateRecipeIntent.OnExitDiscard -> onExitDiscard()
            CreateRecipeIntent.OnExitSaveDraft -> onExitSaveDraft()
            CreateRecipeIntent.OnSave -> onSave()
            is CreateRecipeIntent.OnTitleChange -> updateState { it.copy(title = intent.value) }
            is CreateRecipeIntent.OnDietToggle -> onDietToggle(intent.type)
            is CreateRecipeIntent.OnCategoryToggle -> onCategoryToggle(intent.category)
            is CreateRecipeIntent.OnCustomCategoryInputChange ->
                updateState { it.copy(customCategoryInput = intent.value) }
            CreateRecipeIntent.OnAddCustomCategory -> onAddCustomCategory()
            is CreateRecipeIntent.OnIngredientNameChange -> updateIngredient(intent.localId) {
                it.copy(name = intent.value)
            }
            is CreateRecipeIntent.OnIngredientDescriptionChange -> updateIngredient(intent.localId) {
                it.copy(description = intent.value)
            }
            is CreateRecipeIntent.OnRemoveIngredient -> onRemoveIngredient(intent.localId)
            is CreateRecipeIntent.OnAddIngredientAfter -> onAddIngredientAfter(intent.localId)
            CreateRecipeIntent.OnPhotoClick -> updateState { it.copy(showImageSourceSheet = true) }
            CreateRecipeIntent.OnDismissImageSourceSheet ->
                updateState { it.copy(showImageSourceSheet = false) }
            is CreateRecipeIntent.OnImageSourceSelected -> {
                updateState { it.copy(showImageSourceSheet = false) }
                sendEvent(CreateRecipeEvent.LaunchImagePicker(intent.source))
            }
            is CreateRecipeIntent.OnImagePicked -> updateState { it.copy(imagePath = intent.path) }
            is CreateRecipeIntent.OnOpenPicker -> updateState { it.copy(activePicker = intent.type) }
            CreateRecipeIntent.OnDismissPicker -> updateState { it.copy(activePicker = null) }
            is CreateRecipeIntent.OnServingsSelected ->
                updateState { it.copy(servings = intent.servings, activePicker = null) }
            is CreateRecipeIntent.OnCookTimeSelected ->
                updateState { it.copy(readyInMinutes = intent.minutes, activePicker = null) }
        }
    }

    private fun observeCategories() {
        observeCategoriesJob?.cancel()
        observeCategoriesJob = launch {
            observeRecipeDishTypes()
                .catch { e -> logger.e(e) { "Failed to observe dish types" } }
                .collectLatest { categories ->
                    updateState { it.copy(availableCategories = categories) }
                }
        }
    }

    private fun loadDraft() = launch {
        val draft = observeRecipeDraft() ?: return@launch
        val ingredients = draft.ingredients.map { item ->
            IngredientFormItem(
                localId = item.localId.ifBlank { newIngredientId() },
                name = item.name,
                description = item.description,
            )
        }.ifEmpty { listOf(IngredientFormItem(localId = newIngredientId())) }
        val loaded = CreateRecipeState(
            imagePath = draft.imagePath,
            title = draft.title,
            servings = draft.servings,
            readyInMinutes = draft.readyInMinutes,
            vegetarian = draft.vegetarian,
            vegan = draft.vegan,
            glutenFree = draft.glutenFree,
            dairyFree = draft.dairyFree,
            selectedCategories = draft.selectedCategories.toSet(),
            ingredients = ingredients,
        )
        baselineSnapshot = loaded
        updateState { it.copy(
            imagePath = loaded.imagePath,
            title = loaded.title,
            servings = loaded.servings,
            readyInMinutes = loaded.readyInMinutes,
            vegetarian = loaded.vegetarian,
            vegan = loaded.vegan,
            glutenFree = loaded.glutenFree,
            dairyFree = loaded.dairyFree,
            selectedCategories = loaded.selectedCategories,
            ingredients = loaded.ingredients,
        ) }
    }

    private fun loadForEdit(id: Long) = launch {
        getUserRecipeById(id)
            .onSuccess { recipe ->
                if (recipe == null) {
                    logger.e { "User recipe $id not found for edit" }
                    sendEvent(CreateRecipeEvent.NavigateBack)
                    return@launch
                }
                applyPrefill(prefillFromRecipe(recipe.toRecipeFormSource(), isEditMode = true))
            }
            .onFailure { e ->
                logger.e(e) { "Failed to load user recipe $id for edit" }
                sendEvent(CreateRecipeEvent.NavigateBack)
            }
    }

    private fun loadFromRecipe(recipeId: Int) = launch {
        getRecipeById(recipeId)
            .onSuccess { recipe ->
                applyPrefill(prefillFromRecipe(recipe.toRecipeFormSource(), isEditMode = false))
            }
            .onFailure { e ->
                logger.e(e) { "Failed to load recipe $recipeId for customize" }
                sendEvent(CreateRecipeEvent.NavigateBack)
            }
    }

    private fun applyPrefill(loaded: CreateRecipeState) {
        baselineSnapshot = loaded
        updateState {
            it.copy(
                isEditMode = loaded.isEditMode,
                imagePath = loaded.imagePath,
                title = loaded.title,
                servings = loaded.servings,
                readyInMinutes = loaded.readyInMinutes,
                vegetarian = loaded.vegetarian,
                vegan = loaded.vegan,
                glutenFree = loaded.glutenFree,
                dairyFree = loaded.dairyFree,
                selectedCategories = loaded.selectedCategories,
                ingredients = loaded.ingredients,
            )
        }
    }

    private fun prefillFromRecipe(
        source: RecipeFormSource,
        isEditMode: Boolean,
    ): CreateRecipeState {
        val ingredients = source.ingredients.map { ingredient ->
            IngredientFormItem(
                localId = newIngredientId(),
                name = ingredient.name,
                description = ingredient.description,
            )
        }.ifEmpty { listOf(IngredientFormItem(localId = newIngredientId())) }
        return CreateRecipeState(
            isEditMode = isEditMode,
            imagePath = source.imagePath,
            title = source.title,
            servings = source.servings,
            readyInMinutes = source.readyInMinutes,
            vegetarian = source.vegetarian,
            vegan = source.vegan,
            glutenFree = source.glutenFree,
            dairyFree = source.dairyFree,
            selectedCategories = source.dishTypes.toSet(),
            ingredients = ingredients,
        )
    }

    private data class RecipeFormSource(
        val imagePath: String?,
        val title: String,
        val servings: Int,
        val readyInMinutes: Int,
        val vegetarian: Boolean,
        val vegan: Boolean,
        val glutenFree: Boolean,
        val dairyFree: Boolean,
        val dishTypes: List<String>,
        val ingredients: List<IngredientFormSource>,
    )

    private data class IngredientFormSource(
        val name: String,
        val description: String,
    )

    private fun Recipe.toRecipeFormSource(): RecipeFormSource = RecipeFormSource(
        imagePath = image,
        title = title,
        servings = servings ?: 1,
        readyInMinutes = readyInMinutes ?: 5,
        vegetarian = vegetarian,
        vegan = vegan,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        dishTypes = dishTypes,
        ingredients = extendedIngredients.map {
            IngredientFormSource(
                name = it.name.orEmpty(),
                description = it.original.orEmpty(),
            )
        },
    )

    private fun UserRecipe.toRecipeFormSource(): RecipeFormSource = RecipeFormSource(
        imagePath = image,
        title = title,
        servings = servings ?: 1,
        readyInMinutes = readyInMinutes ?: 5,
        vegetarian = vegetarian,
        vegan = vegan,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        dishTypes = dishTypes,
        ingredients = extendedIngredients.map {
            IngredientFormSource(
                name = it.name.orEmpty(),
                description = it.original.orEmpty(),
            )
        },
    )

    private fun onBackPressed() {
        if (hasUnsavedChanges()) {
            updateState { it.copy(showExitDialog = true) }
        } else {
            sendEvent(CreateRecipeEvent.NavigateBack)
        }
    }

    private fun onExitDiscard() = launch {
        updateState { it.copy(showExitDialog = false) }
        if (!state.isEditMode) {
            clearRecipeDraft().onFailure { e ->
                logger.e(e) { "Failed to clear recipe draft" }
            }
        }
        sendEvent(CreateRecipeEvent.NavigateBack)
    }

    private fun onExitSaveDraft() = launch {
        if (state.isEditMode) {
            updateState { it.copy(showExitDialog = false) }
            sendEvent(CreateRecipeEvent.NavigateBack)
            return@launch
        }
        saveRecipeDraft(state.toDraft())
            .onFailure { e -> logger.e(e) { "Failed to save recipe draft" } }
        updateState { it.copy(showExitDialog = false) }
        sendEvent(CreateRecipeEvent.NavigateBack)
    }

    private fun onSave() = launch {
        if (!state.isSaveEnabled || state.isSaving) return@launch
        updateState { it.copy(isSaving = true) }
        val ingredients = state.ingredients
            .filter { it.name.isNotBlank() && it.description.isNotBlank() }
            .map { Ingredient(id = null, name = it.name.trim(), original = it.description.trim(), image = null) }
        val image = state.imagePath ?: return@launch

        val result = when (val currentMode = mode) {
            is CreateRecipeMode.Edit -> updateUserRecipe(
                userRecipeId = currentMode.userRecipeId,
                title = state.title,
                image = image,
                readyInMinutes = state.readyInMinutes,
                servings = state.servings,
                vegetarian = state.vegetarian,
                vegan = state.vegan,
                glutenFree = state.glutenFree,
                dairyFree = state.dairyFree,
                dishTypes = state.selectedCategories.toList(),
                ingredients = ingredients,
            )
            else -> createUserRecipe(
                title = state.title,
                image = image,
                readyInMinutes = state.readyInMinutes,
                servings = state.servings,
                vegetarian = state.vegetarian,
                vegan = state.vegan,
                glutenFree = state.glutenFree,
                dairyFree = state.dairyFree,
                dishTypes = state.selectedCategories.toList(),
                ingredients = ingredients,
            )
        }

        result
            .onSuccess {
                if (!state.isEditMode) {
                    clearRecipeDraft().onFailure { e ->
                        logger.e(e) { "Failed to clear recipe draft after save" }
                    }
                }
                updateState { it.copy(isSaving = false) }
                sendEvent(CreateRecipeEvent.NavigateBack)
            }
            .onFailure { e ->
                logger.e(e) { "Failed to save user recipe" }
                updateState { it.copy(isSaving = false) }
            }
    }

    private fun onDietToggle(type: DietType) {
        updateState {
            when (type) {
                DietType.Vegetarian -> it.copy(vegetarian = !it.vegetarian)
                DietType.Vegan -> it.copy(vegan = !it.vegan)
                DietType.GlutenFree -> it.copy(glutenFree = !it.glutenFree)
                DietType.DairyFree -> it.copy(dairyFree = !it.dairyFree)
            }
        }
    }

    private fun onCategoryToggle(category: String) {
        updateState {
            val updated = it.selectedCategories.toMutableSet()
            if (category in updated) updated.remove(category) else updated.add(category)
            it.copy(selectedCategories = updated)
        }
    }

    private fun onAddCustomCategory() {
        val value = state.customCategoryInput.trim()
        if (value.isBlank()) return
        val normalized = value.replaceFirstChar { char ->
            if (char.isLowerCase()) char.titlecase() else char.toString()
        }
        updateState {
            it.copy(
                customCategoryInput = "",
                selectedCategories = it.selectedCategories + normalized,
                availableCategories = if (normalized.lowercase() in it.availableCategories.map(String::lowercase)) {
                    it.availableCategories
                } else {
                    it.availableCategories + normalized
                },
            )
        }
    }

    private fun updateIngredient(localId: String, transform: (IngredientFormItem) -> IngredientFormItem) {
        updateState {
            it.copy(
                ingredients = it.ingredients.map { item ->
                    if (item.localId == localId) transform(item) else item
                },
            )
        }
    }

    private fun onRemoveIngredient(localId: String) {
        updateState {
            val updated = it.ingredients.filterNot { item -> item.localId == localId }
            it.copy(
                ingredients = updated.ifEmpty { listOf(IngredientFormItem(localId = newIngredientId())) },
            )
        }
    }

    private fun onAddIngredientAfter(localId: String) {
        val newItem = IngredientFormItem(localId = newIngredientId())
        updateState {
            val index = it.ingredients.indexOfFirst { item -> item.localId == localId }
            if (index < 0) return@updateState it
            val updated = it.ingredients.toMutableList()
            updated.add(index + 1, newItem)
            it.copy(ingredients = updated)
        }
    }

    private fun hasUnsavedChanges(): Boolean = state.toComparableSnapshot() != baselineSnapshot.toComparableSnapshot()

    private fun CreateRecipeState.toComparableSnapshot(): Snapshot = Snapshot(
        imagePath = imagePath,
        title = title.trim(),
        servings = servings,
        readyInMinutes = readyInMinutes,
        vegetarian = vegetarian,
        vegan = vegan,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        selectedCategories = selectedCategories.map { it.lowercase() }.sorted(),
        ingredients = ingredients.map { it.name.trim() to it.description.trim() },
    )

    private fun CreateRecipeState.toDraft(): RecipeDraft = RecipeDraft(
        imagePath = imagePath,
        title = title,
        servings = servings,
        readyInMinutes = readyInMinutes,
        vegetarian = vegetarian,
        vegan = vegan,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        selectedCategories = selectedCategories.toList(),
        ingredients = ingredients.map {
            RecipeDraftIngredient(
                localId = it.localId,
                name = it.name,
                description = it.description,
            )
        },
    )

    private fun newIngredientId(): String = "ing_${nextIngredientId++}_${Random.nextInt()}"

    private data class Snapshot(
        val imagePath: String?,
        val title: String,
        val servings: Int,
        val readyInMinutes: Int,
        val vegetarian: Boolean,
        val vegan: Boolean,
        val glutenFree: Boolean,
        val dairyFree: Boolean,
        val selectedCategories: List<String>,
        val ingredients: List<Pair<String, String>>,
    )
}
