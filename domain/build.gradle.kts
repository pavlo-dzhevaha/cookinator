import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("cookinator.kmp.library")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.kotlinx.coroutines.core)
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.core)

        implementation(projects.dataModel)
        implementation(projects.dataNetwork)
        implementation(projects.dataDatabase)
    }
}
