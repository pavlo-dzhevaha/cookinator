package idp.cookinator.logging

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.platformLogWriter

object AppLogger {
    private var initialized = false

    fun init(minSeverity: Severity = Severity.Debug) {
        if (initialized) return
        Logger.setLogWriters(platformLogWriter())
        Logger.setMinSeverity(minSeverity)
        initialized = true
    }

    fun tag(name: String): Logger = Logger.withTag(name)
}
