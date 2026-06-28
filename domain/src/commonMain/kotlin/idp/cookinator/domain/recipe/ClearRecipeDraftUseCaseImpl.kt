package idp.cookinator.domain.recipe

import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.preferences.AppStorage

internal class ClearRecipeDraftUseCaseImpl(
    private val appStorage: AppStorage,
) : ClearRecipeDraftUseCase {
    override suspend fun invoke(): Result<Unit> = useCaseIo {
        runCatching { appStorage.setRecipeCreateDraftJson(null) }
    }
}
