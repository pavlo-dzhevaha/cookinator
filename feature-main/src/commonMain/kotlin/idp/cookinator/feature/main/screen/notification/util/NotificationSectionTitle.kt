package idp.cookinator.feature.main.screen.notification.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.notifications_date
import cookinator.localisation.generated.resources.notifications_month_april
import cookinator.localisation.generated.resources.notifications_month_august
import cookinator.localisation.generated.resources.notifications_month_december
import cookinator.localisation.generated.resources.notifications_month_february
import cookinator.localisation.generated.resources.notifications_month_january
import cookinator.localisation.generated.resources.notifications_month_july
import cookinator.localisation.generated.resources.notifications_month_june
import cookinator.localisation.generated.resources.notifications_month_march
import cookinator.localisation.generated.resources.notifications_month_may
import cookinator.localisation.generated.resources.notifications_month_november
import cookinator.localisation.generated.resources.notifications_month_october
import cookinator.localisation.generated.resources.notifications_month_september
import cookinator.localisation.generated.resources.notifications_section_today
import cookinator.localisation.generated.resources.notifications_section_yesterday
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Instant

@Composable
internal fun notificationSectionTitle(
    dayStartEpochMs: Long,
    nowEpochMs: Long = System.currentTimeMillis(),
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String {
    val sectionDate = remember(dayStartEpochMs, timeZone) {
        Instant.fromEpochMilliseconds(dayStartEpochMs).toLocalDateTime(timeZone).date
    }
    val today = remember(nowEpochMs, timeZone) {
        Instant.fromEpochMilliseconds(nowEpochMs).toLocalDateTime(timeZone).date
    }
    val yesterday = remember(today) { today.minus(DatePeriod(days = 1)) }

    return when (sectionDate) {
        today -> stringResource(Res.string.notifications_section_today)
        yesterday -> stringResource(Res.string.notifications_section_yesterday)
        else -> formatDateLabel(sectionDate)
    }
}

@Composable
private fun formatDateLabel(date: LocalDate): String {
    val month = when (date.month) {
        Month.JANUARY -> stringResource(Res.string.notifications_month_january)
        Month.FEBRUARY -> stringResource(Res.string.notifications_month_february)
        Month.MARCH -> stringResource(Res.string.notifications_month_march)
        Month.APRIL -> stringResource(Res.string.notifications_month_april)
        Month.MAY -> stringResource(Res.string.notifications_month_may)
        Month.JUNE -> stringResource(Res.string.notifications_month_june)
        Month.JULY -> stringResource(Res.string.notifications_month_july)
        Month.AUGUST -> stringResource(Res.string.notifications_month_august)
        Month.SEPTEMBER -> stringResource(Res.string.notifications_month_september)
        Month.OCTOBER -> stringResource(Res.string.notifications_month_october)
        Month.NOVEMBER -> stringResource(Res.string.notifications_month_november)
        Month.DECEMBER -> stringResource(Res.string.notifications_month_december)
    }
    return stringResource(
        Res.string.notifications_date,
        month,
        date.day,
        date.year,
    )
}
