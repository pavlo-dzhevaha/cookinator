package idp.cookinator.domain.recipe

import idp.cookinator.model.RecipeDraft

/**
 * Loads the persisted create-recipe draft from app preferences.
 */
interface ObserveRecipeDraftUseCase {
    suspend operator fun invoke(): RecipeDraft?
}
