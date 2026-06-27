package idp.cookinator.notification

import idp.cookinator.feature.navigation.PendingRecipeNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationDeepLinkStore : PendingRecipeNavigation {
    private val _pendingRecipeId = MutableStateFlow<Int?>(null)

    override val pendingRecipeId: StateFlow<Int?> = _pendingRecipeId.asStateFlow()

    override fun setRecipeDetail(recipeId: Int) {
        _pendingRecipeId.value = recipeId
    }

    override fun consumeRecipeDetail() {
        _pendingRecipeId.value = null
    }
}
