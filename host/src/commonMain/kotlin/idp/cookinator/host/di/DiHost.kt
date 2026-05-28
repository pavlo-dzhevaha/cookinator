package idp.cookinator.host.di

import idp.cookinator.host.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val hostModule = module {
    viewModelOf(::AppViewModel)
}
