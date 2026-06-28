package idp.cookinator.domain.di

import idp.cookinator.domain.notification.ClearNotificationsUseCase
import idp.cookinator.domain.notification.ClearNotificationsUseCaseImpl
import idp.cookinator.domain.notification.MarkNotificationReadUseCase
import idp.cookinator.domain.notification.MarkNotificationReadUseCaseImpl
import idp.cookinator.domain.notification.ObserveNotificationsUseCase
import idp.cookinator.domain.notification.ObserveNotificationsUseCaseImpl
import idp.cookinator.domain.notification.SendRecipeReminderUseCase
import idp.cookinator.domain.notification.SendRecipeReminderUseCaseImpl
import idp.cookinator.domain.recipe.ClearRecipeDraftUseCase
import idp.cookinator.domain.recipe.ClearRecipeDraftUseCaseImpl
import idp.cookinator.domain.recipe.CreateUserRecipeUseCase
import idp.cookinator.domain.recipe.CreateUserRecipeUseCaseImpl
import idp.cookinator.domain.recipe.DeleteUserRecipeUseCase
import idp.cookinator.domain.recipe.DeleteUserRecipeUseCaseImpl
import idp.cookinator.domain.recipe.GetRecipeByIdUseCase
import idp.cookinator.domain.recipe.GetRecipeByIdUseCaseImpl
import idp.cookinator.domain.recipe.GetUserRecipeByIdUseCase
import idp.cookinator.domain.recipe.GetUserRecipeByIdUseCaseImpl
import idp.cookinator.domain.recipe.GetRandomRecipesUseCase
import idp.cookinator.domain.recipe.GetRandomRecipesUseCaseImpl
import idp.cookinator.domain.recipe.ObserveDiscoveryRecipesUseCase
import idp.cookinator.domain.recipe.ObserveDiscoveryRecipesUseCaseImpl
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCase
import idp.cookinator.domain.recipe.RecipeDiscoveryStore
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCaseImpl
import idp.cookinator.domain.recipe.ObserveLikedRecipesUseCase
import idp.cookinator.domain.recipe.ObserveLikedRecipesUseCaseImpl
import idp.cookinator.domain.recipe.ObserveRecipeDishTypesUseCase
import idp.cookinator.domain.recipe.ObserveRecipeDishTypesUseCaseImpl
import idp.cookinator.domain.recipe.ObserveRecipeDraftUseCase
import idp.cookinator.domain.recipe.ObserveRecipeDraftUseCaseImpl
import idp.cookinator.domain.recipe.ObserveUserRecipesUseCase
import idp.cookinator.domain.recipe.ObserveUserRecipesUseCaseImpl
import idp.cookinator.domain.recipe.SaveRecipeDraftUseCase
import idp.cookinator.domain.recipe.SaveRecipeDraftUseCaseImpl
import idp.cookinator.domain.recipe.UpdateUserRecipeUseCase
import idp.cookinator.domain.recipe.UpdateUserRecipeUseCaseImpl
import idp.cookinator.domain.recipe.ObserveRecentlyViewedUseCase
import idp.cookinator.domain.recipe.ObserveRecentlyViewedUseCaseImpl
import idp.cookinator.domain.recipe.ObserveRecipesByDishTypeUseCase
import idp.cookinator.domain.recipe.ObserveRecipesByDishTypeUseCaseImpl
import idp.cookinator.domain.recipe.RecordRecipeViewedUseCase
import idp.cookinator.domain.recipe.RecordRecipeViewedUseCaseImpl
import idp.cookinator.domain.recipe.SetRecipeLikedUseCase
import idp.cookinator.domain.recipe.SetRecipeLikedUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    singleOf(::RecipeDiscoveryStore)
    singleOf(::GetRandomRecipesUseCaseImpl) bind GetRandomRecipesUseCase::class
    singleOf(::GetRecipeByIdUseCaseImpl) bind GetRecipeByIdUseCase::class
    singleOf(::ObserveDiscoveryRecipesUseCaseImpl) bind ObserveDiscoveryRecipesUseCase::class
    singleOf(::ObserveLikedRecipeIdsUseCaseImpl) bind ObserveLikedRecipeIdsUseCase::class
    singleOf(::ObserveLikedRecipesUseCaseImpl) bind ObserveLikedRecipesUseCase::class
    singleOf(::ObserveRecipeDishTypesUseCaseImpl) bind ObserveRecipeDishTypesUseCase::class
    singleOf(::ObserveRecipesByDishTypeUseCaseImpl) bind ObserveRecipesByDishTypeUseCase::class
    singleOf(::RecordRecipeViewedUseCaseImpl) bind RecordRecipeViewedUseCase::class
    singleOf(::ObserveRecentlyViewedUseCaseImpl) bind ObserveRecentlyViewedUseCase::class
    singleOf(::SetRecipeLikedUseCaseImpl) bind SetRecipeLikedUseCase::class
    singleOf(::CreateUserRecipeUseCaseImpl) bind CreateUserRecipeUseCase::class
    singleOf(::DeleteUserRecipeUseCaseImpl) bind DeleteUserRecipeUseCase::class
    singleOf(::UpdateUserRecipeUseCaseImpl) bind UpdateUserRecipeUseCase::class
    singleOf(::ObserveUserRecipesUseCaseImpl) bind ObserveUserRecipesUseCase::class
    singleOf(::GetUserRecipeByIdUseCaseImpl) bind GetUserRecipeByIdUseCase::class
    singleOf(::ObserveRecipeDraftUseCaseImpl) bind ObserveRecipeDraftUseCase::class
    singleOf(::SaveRecipeDraftUseCaseImpl) bind SaveRecipeDraftUseCase::class
    singleOf(::ClearRecipeDraftUseCaseImpl) bind ClearRecipeDraftUseCase::class
    singleOf(::ObserveNotificationsUseCaseImpl) bind ObserveNotificationsUseCase::class
    singleOf(::MarkNotificationReadUseCaseImpl) bind MarkNotificationReadUseCase::class
    singleOf(::ClearNotificationsUseCaseImpl) bind ClearNotificationsUseCase::class
    singleOf(::SendRecipeReminderUseCaseImpl) bind SendRecipeReminderUseCase::class
}
