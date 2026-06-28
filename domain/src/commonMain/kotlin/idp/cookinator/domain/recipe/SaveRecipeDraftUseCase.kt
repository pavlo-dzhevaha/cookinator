package idp.cookinator.domain.recipe

import idp.cookinator.model.RecipeDraft

/**
 * Persists the create-recipe draft to app preferences.
 */
interface SaveRecipeDraftUseCase {
    suspend operator fun invoke(draft: RecipeDraft): Result<Unit>
}
