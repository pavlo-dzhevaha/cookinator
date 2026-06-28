package idp.cookinator.feature.createrecipe.screen.create

internal object CreateRecipeOptions {
    val servingOptions: List<Int> = (1..12).toList() + SERVINGS_12_PLUS_VALUE

    val cookTimeOptions: List<Int> = buildList {
        addAll((5..60 step 5))
        addAll((90..720 step 30))
    }

    const val SERVINGS_12_PLUS_VALUE = 13
}
