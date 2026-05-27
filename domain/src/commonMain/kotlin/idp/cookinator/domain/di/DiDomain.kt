package idp.cookinator.domain.di

import idp.cookinator.domain.DomainManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::DomainManager)
}
