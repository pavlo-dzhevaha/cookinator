# Cookinator build-logic

Gradle convention plugins for Kotlin Multiplatform modules (AGP 9 + `com.android.kotlin.multiplatform.library`).

## Setup

- Root `settings.gradle.kts` → `pluginManagement { includeBuild("build-logic") }`
- Version catalog shared via `build-logic/settings.gradle.kts` (`gradle/libs.versions.toml`)

## Plugins

### `cookinator.kmp.library`

Applies: `org.jetbrains.kotlin.multiplatform`, `com.android.kotlin.multiplatform.library`

Configures via `androidLibrary` extension API:
- `namespace` = `libs.versions.namespace` + suffix
- `compileSdk`, `minSdk` from version catalog
- `jvm()` desktop target
- `androidResources` when enabled

### `cookinator.compose`

Applies: Compose Multiplatform + Compose Compiler

Adds `compose-uiTooling` to `androidMain` when `composeCookinator.androidTooling = true` (default).

### `cookinator.feature`

Applies: `cookinator.kmp.library` + `cookinator.compose`

Adds common feature dependencies in `commonMain`:
- compose runtime, foundation, ui, material3, uiToolingPreview
- koin BOM + koin-compose + koin-compose-viewmodel

## Module mapping

| Plugins | Modules |
|---------|---------|
| `cookinator.kmp.library` | data-model, data-preferences, data-network, data-database, domain, notification |
| kmp.library + compose | localisation, navigation, core-ui, host |
| `cookinator.feature` | feature-splash, feature-onboarding, feature-main, feature-settings |
| unchanged | androidApp, desktopApp |

## Defaults (`NamespaceDefaults.kt`)

Namespace suffix is derived from the Gradle module name:

| Module | Suffix |
|--------|--------|
| core-ui | `.coreui` |
| data-model | `.model` |
| data-preferences | `.preferences` |
| data-network | `.network` |
| data-database | `.database` |
| feature-main | `.feature.main` |
| feature-splash | `.feature.splash` |
| feature-onboarding | `.feature.onboarding` |
| feature-settings | `.feature.settings` |
| other | `.<name-with-dashes-as-dots>` (e.g. `domain` → `.domain`) |

`androidResources = true` for `core-ui` and `localisation`.

Override in a module only when necessary:

```kotlin
cookinator {
    namespaceSuffix = ".custom"
    androidResources = true
}
```

Prefer adding new mappings to `NamespaceDefaults.kt` over per-module overrides.

## Minimal examples

**Library (domain):**

```kotlin
plugins {
    id("cookinator.kmp.library")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(projects.dataModel)
    }
}
```

**Feature:**

```kotlin
plugins {
    id("cookinator.feature")
}

compose {
    resources {
        generateResClass = never
    }
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(projects.navigation)
        implementation(projects.localisation)
    }
}
```

**Compose library (navigation):**

```kotlin
plugins {
    id("cookinator.kmp.library")
    id("cookinator.compose")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        api(projects.coreUi)
    }
}
```

**Room module (data-database):**

```kotlin
plugins {
    id("cookinator.kmp.library")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin { /* dependencies */ }

dependencies {
    add("kspCommonMainMetadata", libs.room.compiler)
    add("kspAndroid", libs.room.compiler)
    add("kspJvm", libs.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}
```

## Adding a new module

1. `settings.gradle.kts` → `include(":module-name")`
2. Pick plugin combo from the table above
3. If namespace suffix is non-obvious → add to `NamespaceDefaults.kt`
4. Verify: `./gradlew assembleDebug compileKotlinJvm`

## File layout

```
build-logic/
├── settings.gradle.kts
├── README.md
└── convention/
    ├── build.gradle.kts
    └── src/main/kotlin/idp/cookinator/convention/
        ├── KmpLibraryConventionPlugin.kt
        ├── ComposeConventionPlugin.kt
        ├── FeatureConventionPlugin.kt
        ├── ConfigureAndroidLibrary.kt
        ├── NamespaceDefaults.kt
        ├── CookinatorExtension.kt
        ├── ComposeCookinatorExtension.kt
        ├── VersionCatalogExtensions.kt
        └── ProjectExtensions.kt
```
