package idp.cookinator.domain.di

import idp.cookinator.domain.notification.ClearNotificationsUseCase
import idp.cookinator.domain.notification.ClearNotificationsUseCaseImpl
import idp.cookinator.domain.notification.MarkNotificationReadUseCase
import idp.cookinator.domain.notification.MarkNotificationReadUseCaseImpl
import idp.cookinator.domain.notification.ObserveNotificationsUseCase
import idp.cookinator.domain.notification.ObserveNotificationsUseCaseImpl
import idp.cookinator.domain.notification.SendRecipeReminderUseCase
import idp.cookinator.domain.notification.SendRecipeReminderUseCaseImpl
import idp.cookinator.domain.recipe.GetRandomRecipesUseCase
import idp.cookinator.domain.recipe.GetRandomRecipesUseCaseImpl
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCase
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCaseImpl
import idp.cookinator.domain.recipe.ObserveLikedRecipesUseCase
import idp.cookinator.domain.recipe.ObserveLikedRecipesUseCaseImpl
import idp.cookinator.domain.recipe.SetRecipeLikedUseCase
import idp.cookinator.domain.recipe.SetRecipeLikedUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetRandomRecipesUseCaseImpl) bind GetRandomRecipesUseCase::class
    singleOf(::ObserveLikedRecipeIdsUseCaseImpl) bind ObserveLikedRecipeIdsUseCase::class
    singleOf(::ObserveLikedRecipesUseCaseImpl) bind ObserveLikedRecipesUseCase::class
    singleOf(::SetRecipeLikedUseCaseImpl) bind SetRecipeLikedUseCase::class
    singleOf(::ObserveNotificationsUseCaseImpl) bind ObserveNotificationsUseCase::class
    singleOf(::MarkNotificationReadUseCaseImpl) bind MarkNotificationReadUseCase::class
    singleOf(::ClearNotificationsUseCaseImpl) bind ClearNotificationsUseCase::class
    singleOf(::SendRecipeReminderUseCaseImpl) bind SendRecipeReminderUseCase::class
}
