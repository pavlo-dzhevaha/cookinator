package idp.cookinator.feature.recipe.screen.detail

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.GetRecipeByIdUseCase
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
    private val recipeId: Int,
    private val getRecipeById: GetRecipeByIdUseCase,
    private val observeLikedRecipeIds: ObserveLikedRecipeIdsUseCase,
    private val setRecipeLiked: SetRecipeLikedUseCase,
    private val recordRecipeViewed: RecordRecipeViewedUseCase,
) : MviViewModel<RecipeDetailState, RecipeDetailIntent, RecipeDetailEvent>(RecipeDetailState.initialState) {

    private var observeLikedJob: Job? = null

    init {
        loadRecipe()
        observeSavedState()
    }

    override fun onIntent(intent: RecipeDetailIntent) {
        when (intent) {
            RecipeDetailIntent.OnRetry -> loadRecipe()
            RecipeDetailIntent.OnToggleFavorite -> onToggleFavorite()
        }
    }

    private fun onToggleFavorite() = launch {
        val isSaved = state.isSaved
        setRecipeLiked(
            recipeId = recipeId,
            isLiked = !isSaved,
        )
    }

    private fun loadRecipe() = launch {
        updateState { it.copy(uiState = UiState.LOADING) }
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

    private fun observeSavedState() {
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
