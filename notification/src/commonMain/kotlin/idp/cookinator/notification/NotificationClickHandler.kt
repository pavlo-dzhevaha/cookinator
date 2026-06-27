package idp.cookinator.notification

import com.mmk.kmpnotifier.KMPNotifier
import com.mmk.kmpnotifier.notification.PayloadData
import idp.cookinator.domain.DomainManager
import idp.cookinator.feature.navigation.PendingRecipeNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NotificationClickHandler(
    private val domain: DomainManager,
    private val pendingRecipeNavigation: PendingRecipeNavigation,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var isRegistered = false

    fun register() {
        if (isRegistered) return
        isRegistered = true
        KMPNotifier.addListener(
            object : KMPNotifier.Listener {
                override fun onNotificationClicked(data: PayloadData) {
                    handleNotificationClick(data)
                }
            },
        )
    }

    private fun handleNotificationClick(data: PayloadData) {
        data.payloadLong(NotificationPayloadKeys.NOTIFICATION_ID)
            ?.let { notificationId ->
                scope.launch {
                    domain.markNotificationRead(notificationId)
                }
            }
        val recipeId = data.payloadInt(NotificationPayloadKeys.RECIPE_ID) ?: return
        pendingRecipeNavigation.setRecipeDetail(recipeId)
    }
}
