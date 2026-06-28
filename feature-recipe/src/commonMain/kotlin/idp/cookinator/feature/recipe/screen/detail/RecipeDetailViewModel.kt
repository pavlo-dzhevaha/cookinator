package idp.cookinator.feature.recipe.screen.detail

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.GetRecipeByIdUseCase
import idp.cookinator.domain.recipe.GetUserRecipeByIdUseCase
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCase
import idp.cookinator.domain.recipe.RecordRecipeViewedUseCase
import idp.cookinator.domain.recipe.SetRecipeLikedUseCase
import idp.cookinator.feature.recipe.screen.detail.contract.RecipeDetailEvent
import idp.cookinator.feature.recipe.screen.detail.contract.RecipeDetailIntent
import idp.cookinator.feature.recipe.screen.detail.contract.RecipeDetailState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest

internal class RecipeDetailViewModel(
    private val args: RecipeDetailArgs,
    private val getRecipeById: GetRecipeByIdUseCase,
    private val getUserRecipeById: GetUserRecipeByIdUseCase,
    private val observeLikedRecipeIds: ObserveLikedRecipeIdsUseCase,
    private val setRecipeLiked: SetRecipeLikedUseCase,
    private val recordRecipeViewed: RecordRecipeViewedUseCase,
) : MviViewModel<RecipeDetailState, RecipeDetailIntent, RecipeDetailEvent>(
    RecipeDetailState.initialState.copy(
        isUserRecipe = args.userRecipeId != null,
        userRecipeId = args.userRecipeId,
    ),
) {

    private var observeLikedJob: Job? = null

    init {
        loadRecipe()
        if (args.recipeId != null) {
            observeSavedState()
        }
    }

    override fun onIntent(intent: RecipeDetailIntent) {
        when (intent) {
            RecipeDetailIntent.OnRetry -> loadRecipe()
            RecipeDetailIntent.OnToggleFavorite -> onToggleFavorite()
            RecipeDetailIntent.OnEdit -> onEdit()
        }
    }

    private fun onEdit() {
        val userRecipeId = args.userRecipeId ?: return
        sendEvent(RecipeDetailEvent.NavigateToEdit(userRecipeId))
    }

    private fun onToggleFavorite() = launch {
        val recipeId = args.recipeId ?: return@launch
        val isSaved = state.isSaved
        setRecipeLiked(
            recipeId = recipeId,
            isLiked = !isSaved,
        )
    }

    private fun loadRecipe() = launch {
        updateState { it.copy(uiState = UiState.LOADING) }
        when (val userRecipeId = args.userRecipeId) {
            null -> loadSpoonacularRecipe(args.recipeId!!)
            else -> loadUserRecipe(userRecipeId)
        }
    }

    private suspend fun loadSpoonacularRecipe(recipeId: Int) {
        getRecipeById(recipeId)
            .onSuccess { recipe ->
                recordRecipeViewed(recipeId).onFailure { e ->
                    logger.e(e) { "Failed to record recipe view $recipeId" }
                }
                updateState {
                    it.copy(
                        uiState = UiState.SUCCESS,
                        recipe = recipe,
                    )
                }
            }.onFailure { e ->
                logger.e(e) { "Failed to load recipe $recipeId" }
                updateState { it.copy(uiState = UiState.ERROR) }
            }
    }

    private suspend fun loadUserRecipe(userRecipeId: Long) {
        getUserRecipeById(userRecipeId)
            .onSuccess { userRecipe ->
                if (userRecipe == null) {
                    updateState { it.copy(uiState = UiState.ERROR) }
                    return
                }
                updateState {
                    it.copy(
                        uiState = UiState.SUCCESS,
                        recipe = userRecipe.toRecipe(),
                        isUserRecipe = true,
                        userRecipeId = userRecipeId,
                    )
                }
            }
            .onFailure { e ->
                logger.e(e) { "Failed to load user recipe $userRecipeId" }
                updateState { it.copy(uiState = UiState.ERROR) }
            }
    }

    private fun observeSavedState() {
        val recipeId = args.recipeId ?: return
        observeLikedJob?.cancel()
        observeLikedJob = launch {
            observeLikedRecipeIds()
                .catch { e ->
                    logger.e(e) { "Failed to observe liked recipe ids" }
                }
                .collectLatest { likedIds ->
                    updateState { it.copy(isSaved = recipeId in likedIds) }
                }
        }
    }
}
