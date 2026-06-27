import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("cookinator.kmp.library")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.kotlinx.coroutines.core)
        implementation(libs.multiplatform.settings)
        implementation(libs.multiplatform.settings.coroutines)
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.core)
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }
}
