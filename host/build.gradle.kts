import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("cookinator.kmp.library")
    id("cookinator.compose")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.compose.runtime)
        implementation(libs.compose.foundation)
        implementation(libs.compose.ui)
        implementation(libs.compose.material3)
        implementation(libs.androidx.lifecycle.runtimeCompose)
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.compose)
        implementation(libs.koin.compose.viewmodel)
        implementation(libs.kmp.notifier)

        implementation(projects.coreUi)
        implementation(projects.navigation)
        implementation(projects.dataPreferences)
        implementation(projects.dataNetwork)
        implementation(projects.dataDatabase)
        implementation(projects.domain)
        implementation(projects.notification)
        implementation(projects.featureSplash)
        implementation(projects.featureOnboarding)
        implementation(projects.featureMain)
        implementation(projects.featureSettings)
    }
}
