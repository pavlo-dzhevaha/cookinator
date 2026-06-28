import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("cookinator.kmp.library")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.kotlinx.coroutines.core)
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.core)
        implementation(libs.ktor.serialization.kotlinx.json)

        implementation(projects.dataModel)
        implementation(projects.dataNetwork)
        implementation(projects.dataDatabase)
        implementation(projects.dataPreferences)
        implementation(projects.coreLogging)
    }
}
