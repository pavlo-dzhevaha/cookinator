package idp.cookinator.feature.main.screen.notification.util

import idp.cookinator.feature.main.screen.notification.model.NotificationSection
import idp.cookinator.feature.main.screen.notification.model.NotificationUiModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

internal fun groupNotificationsByDay(
    items: List<NotificationUiModel>,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): List<NotificationSection> {
    if (items.isEmpty()) return emptyList()

    return items
        .sortedByDescending { it.createdAt }
        .groupBy { startOfDayEpoch(it.createdAt, timeZone) }
        .map { (dayStart, sectionItems) ->
            NotificationSection(
                dayStartEpochMs = dayStart,
                items = sectionItems.sortedByDescending { it.createdAt },
            )
        }
        .sortedByDescending { it.dayStartEpochMs }
}

internal fun startOfDayEpoch(
    epochMs: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): Long {
    val date = Instant.fromEpochMilliseconds(epochMs).toLocalDateTime(timeZone).date
    return date.atStartOfDayIn(timeZone).toEpochMilliseconds()
}
