import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("cookinator.kmp.library")
    id("cookinator.compose")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.core)
        implementation(libs.kmp.notifier.local)

        implementation(projects.navigation)
        implementation(projects.dataDatabase)
        implementation(projects.dataModel)
        implementation(projects.domain)
        implementation(projects.localisation)

        implementation(libs.compose.components.resources)
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.androidx.workmanager)
        }
    }
}
