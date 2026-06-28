package idp.cookinator.domain.recipe

/**
 * Clears the persisted create-recipe draft.
 */
interface ClearRecipeDraftUseCase {
    suspend operator fun invoke(): Result<Unit>
}
