package idp.cookinator.feature.createrecipe.di

import idp.cookinator.feature.createrecipe.screen.create.CreateRecipeMode
import idp.cookinator.feature.createrecipe.screen.create.CreateRecipeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureCreateRecipeModule = module {
    viewModel { parameters ->
        CreateRecipeViewModel(
            mode = parameters.get(),
            observeRecipeDishTypes = get(),
            observeRecipeDraft = get(),
            saveRecipeDraft = get(),
            clearRecipeDraft = get(),
            createUserRecipe = get(),
            updateUserRecipe = get(),
            getUserRecipeById = get(),
            getRecipeById = get(),
        )
    }
}
