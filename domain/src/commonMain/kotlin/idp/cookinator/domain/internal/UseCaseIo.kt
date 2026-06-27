package idp.cookinator.domain.internal

import idp.cookinator.logging.AppLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend fun <T> useCaseIo(
    block: suspend CoroutineScope.() -> T,
): T = withContext(Dispatchers.IO, block)

internal fun useCaseLog(tag: String, message: () -> String) {
    AppLogger.tag(tag).d { message() }
}
