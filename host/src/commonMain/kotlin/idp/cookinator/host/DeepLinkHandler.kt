package idp.cookinator.host

import idp.cookinator.feature.navigation.DeepLinkParser
import idp.cookinator.feature.navigation.PendingRecipeNavigation
import idp.cookinator.logging.AppLogger

class DeepLinkHandler(
    private val pendingRecipeNavigation: PendingRecipeNavigation,
) {
    private val logger = AppLogger.tag("DeepLink")

    fun handle(uri: String) {
        val recipeId = DeepLinkParser.parseRecipeId(uri)
        if (recipeId == null) {
            logger.w { "Unrecognized deep link: $uri" }
            return
        }
        logger.d { "Pending recipe detail navigation for id=$recipeId" }
        pendingRecipeNavigation.setRecipeDetail(recipeId)
    }
}
