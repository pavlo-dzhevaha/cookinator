package idp.cookinator.feature.navigation

object DeepLinkParser {
    private const val SCHEME = "app"
    private const val HOST = "recipe"
    private const val PARAM_ID = "id"

    fun parseRecipeId(uriString: String): Int? {
        val withoutFragment = uriString.substringBefore('#')
        val schemeEnd = withoutFragment.indexOf("://")
        if (schemeEnd == -1) return null

        val scheme = withoutFragment.substring(0, schemeEnd)
        if (scheme != SCHEME) return null

        val afterScheme = withoutFragment.substring(schemeEnd + 3)
        val queryStart = afterScheme.indexOf('?')
        val host = when {
            queryStart == -1 -> afterScheme.substringBefore('/')
            else -> afterScheme.substring(0, queryStart).substringBefore('/')
        }
        if (host != HOST || queryStart == -1) return null

        val query = afterScheme.substring(queryStart + 1).substringBefore('/')
        return query
            .split('&')
            .firstNotNullOfOrNull { param ->
                val parts = param.split('=', limit = 2)
                if (parts.size == 2 && parts[0] == PARAM_ID) parts[1].toIntOrNull() else null
            }
    }
}
