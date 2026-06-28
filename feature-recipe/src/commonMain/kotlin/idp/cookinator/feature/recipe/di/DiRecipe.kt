package idp.cookinator.feature.recipe.di

import idp.cookinator.feature.recipe.screen.detail.RecipeDetailArgs
import idp.cookinator.feature.recipe.screen.detail.RecipeDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureRecipeModule = module {
    viewModel { parameters ->
        RecipeDetailViewModel(
            args = parameters.get<RecipeDetailArgs>(),
            getRecipeById = get(),
            getUserRecipeById = get(),
            observeLikedRecipeIds = get(),
            setRecipeLiked = get(),
            recordRecipeViewed = get(),
        )
    }
}
