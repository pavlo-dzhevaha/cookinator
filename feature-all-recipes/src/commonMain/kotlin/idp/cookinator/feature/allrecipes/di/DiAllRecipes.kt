package idp.cookinator.feature.allrecipes.di

import idp.cookinator.feature.allrecipes.screen.AllRecipesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureAllRecipesModule = module {
    viewModelOf(::AllRecipesViewModel)
}
