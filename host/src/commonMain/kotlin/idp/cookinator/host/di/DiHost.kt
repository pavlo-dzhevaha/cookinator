package idp.cookinator.host.di

import idp.cookinator.host.AppViewModel
import idp.cookinator.host.DeepLinkHandler
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val hostModule = module {
    singleOf(::DeepLinkHandler)
    viewModelOf(::AppViewModel)
}
