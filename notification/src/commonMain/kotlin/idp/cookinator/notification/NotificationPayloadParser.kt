package idp.cookinator.notification

import com.mmk.kmpnotifier.notification.PayloadData

fun PayloadData.payloadLong(key: String): Long? = when (val value = this[key]) {
    is Long -> value
    is Int -> value.toLong()
    is Number -> value.toLong()
    is String -> value.toLongOrNull()
    else -> null
}

fun PayloadData.payloadInt(key: String): Int? = when (val value = this[key]) {
    is Int -> value
    is Number -> value.toInt()
    is String -> value.toIntOrNull()
    else -> null
}
