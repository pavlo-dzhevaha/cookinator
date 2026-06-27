package idp.cookinator.convention

import org.gradle.api.Project

internal fun Project.defaultNamespaceSuffix(): String = when (name) {
    "core-ui" -> ".coreui"
    "data-model" -> ".model"
    "data-preferences" -> ".preferences"
    "data-network" -> ".network"
    "data-database" -> ".database"
    "feature-main" -> ".feature.main"
    "feature-splash" -> ".feature.splash"
    "feature-onboarding" -> ".feature.onboarding"
    "feature-settings" -> ".feature.settings"
    else -> ".${name.replace('-', '.')}"
}

internal fun Project.defaultAndroidResources(): Boolean = name in setOf("core-ui", "localisation")
