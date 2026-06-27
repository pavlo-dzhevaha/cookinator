import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("cookinator.kmp.library")
    id("cookinator.compose")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        api(libs.androidx.lifecycle.viewmodel.navigation3)
        api(libs.androidx.navigation3.ui)

        api(projects.coreUi)
    }
}
