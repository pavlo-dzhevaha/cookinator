package idp.cookinator.host

import idp.cookinator.notification.NotificationClickHandler
import org.koin.core.context.GlobalContext

fun registerNotificationClickHandler() {
    GlobalContext.get().get<NotificationClickHandler>().register()
}
