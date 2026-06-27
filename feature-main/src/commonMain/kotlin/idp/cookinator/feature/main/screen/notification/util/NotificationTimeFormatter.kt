package idp.cookinator.feature.main.screen.notification.util

internal fun formatRelativeTime(
    createdAt: Long,
    now: Long = System.currentTimeMillis(),
): String {
    val diffMs = (now - createdAt).coerceAtLeast(0)
    val minutes = diffMs / 60_000
    val hours = minutes / 60
    val days = hours / 24
    return when {
        minutes < 1 -> "Just now"
        minutes < 60 -> "${minutes}m"
        hours < 24 -> "${hours}h"
        days < 7 -> "${days}d"
        else -> "${days / 7}w"
    }
}
