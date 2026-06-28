package idp.cookinator.domain.recipe

import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.model.RecipeDraft
import idp.cookinator.preferences.AppStorage
import kotlinx.serialization.json.Json

internal class ObserveRecipeDraftUseCaseImpl(
    private val appStorage: AppStorage,
) : ObserveRecipeDraftUseCase {
    override suspend fun invoke(): RecipeDraft? = useCaseIo {
        val json = appStorage.getRecipeCreateDraftJson() ?: return@useCaseIo null
        runCatching { Json.decodeFromString<RecipeDraft>(json) }.getOrNull()
    }
}
