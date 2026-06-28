package idp.cookinator.domain.recipe

import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.model.RecipeDraft
import idp.cookinator.preferences.AppStorage
import kotlinx.serialization.json.Json

internal class SaveRecipeDraftUseCaseImpl(
    private val appStorage: AppStorage,
) : SaveRecipeDraftUseCase {
    override suspend fun invoke(draft: RecipeDraft): Result<Unit> = useCaseIo {
        runCatching {
            appStorage.setRecipeCreateDraftJson(Json.encodeToString(draft))
        }
    }
}
