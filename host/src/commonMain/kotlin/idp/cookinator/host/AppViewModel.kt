package idp.cookinator.host

import com.mmk.kmpnotifier.KMPNotifier
import com.mmk.kmpnotifier.notification.PayloadData
import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.viewmodel.StateViewModel
import idp.cookinator.feature.navigation.PendingRecipeNavigation
import idp.cookinator.host.contract.State
import idp.cookinator.notification.NotificationPayloadKeys
import idp.cookinator.notification.NotificationScheduler
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class AppViewModel(
    private val storage: AppStorage,
    private val notificationScheduler: NotificationScheduler,
    private val pendingRecipeNavigation: PendingRecipeNavigation,
) : StateViewModel<State>(State.initialState) {
    init {
        observeThemeStyle()
        observeThemeLocale()
        notificationScheduler.startPeriodicReminders()
        registerNotificationClickListener()
    }

    private fun registerNotificationClickListener() {
        KMPNotifier.addListener(
            object : KMPNotifier.Listener {
                override fun onNotificationClicked(data: PayloadData) {
                    val recipeId = data[NotificationPayloadKeys.RECIPE_ID]
                        ?.toString()
                        ?.toIntOrNull()
                        ?: return
                    pendingRecipeNavigation.setRecipeDetail(recipeId)
                }
            },
        )
    }

    private fun observeThemeStyle() = launch {
        storage
            .observeCurrentThemeStyle()
            .distinctUntilChanged()
            .map(ThemeStyle::parse)
            .collectLatest { style ->
                updateState {
                    it.copy(style = style)
                }
            }
    }

    private fun observeThemeLocale() = launch {
        storage
            .observeLanguageTag()
            .distinctUntilChanged()
            .map(ThemeLocale::parse)
            .collectLatest { locale ->
                updateState {
                    it.copy(locale = locale)
                }
            }
    }
}
