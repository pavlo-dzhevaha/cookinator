import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

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
        implementation(libs.compose.components.resources)
        implementation(libs.coil.compose)

        implementation(projects.navigation)
        implementation(projects.localisation)
        implementation(projects.domain)
        implementation(projects.dataModel)
    }
}
