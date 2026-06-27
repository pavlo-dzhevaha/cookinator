import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("cookinator.feature")
    alias(libs.plugins.kotlinSerialization)
}

compose {
    resources {
        generateResClass = never
    }
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.compose.material3.adaptive.navigation3)
        implementation(libs.compose.components.resources)
        implementation(libs.coil.compose)

        implementation(projects.navigation)
        implementation(projects.localisation)
        implementation(projects.domain)
        implementation(projects.notification)
        implementation(projects.dataModel)
        implementation(projects.dataPreferences)
    }
}
