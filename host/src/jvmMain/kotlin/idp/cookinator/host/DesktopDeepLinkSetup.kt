package idp.cookinator.host

import java.awt.Desktop

fun registerDesktopDeepLinkHandlers(args: Array<String>) {
    args.firstOrNull { it.startsWith("app://") }?.let(::handleDeepLink)

    if (System.getProperty("os.name").contains("Mac", ignoreCase = true)) {
        try {
            Desktop.getDesktop().setOpenURIHandler { event ->
                handleDeepLink(event.uri.toString())
            }
        } catch (_: UnsupportedOperationException) {
            // setOpenURIHandler is unsupported on this platform
        }
    }
}
