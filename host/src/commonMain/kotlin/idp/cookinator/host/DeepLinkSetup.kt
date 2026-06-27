package idp.cookinator.host

import org.koin.core.context.GlobalContext

fun handleDeepLink(uri: String) {
    GlobalContext.get().get<DeepLinkHandler>().handle(uri)
}
