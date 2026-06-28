# :core-logging

Centralized logging facade over Kermit.

## Purpose

Single entry point for app-wide logging. Ensures the rest of the codebase never imports Kermit directly.

## Key classes / files

- `AppLogger.kt` — `init(minSeverity)`, `tag(name): Logger`

## Dependencies

**Project modules:** none

**Libraries:** Kermit (api — exposed to dependents)

## Public API / usage

- `AppLogger.init()` — called once in `host/di/AppKoin` platform init (before `startKoin`)
- `AppLogger.tag("MyTag")` — returns a Kermit `Logger` for `.d` / `.w` / `.e` calls

Used by `:domain` (`useCaseLog`), `:core-ui` (`StateViewModel.logger`), and `:host`. Features get logging indirectly through `StateViewModel` or transitive `:core-logging` dependency.
