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
        implementation(libs.compose.components.resources)
        implementation(libs.compose.uiToolingPreview)
        implementation(libs.androidx.lifecycle.viewmodelCompose)
        implementation(libs.androidx.lifecycle.runtimeCompose)
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.compose)
        implementation(libs.coil.compose)
        implementation(libs.coil.network.ktor)

        implementation(projects.localisation)
        implementation(projects.dataModel)
    }
}

compose {
    resources {
        publicResClass = true
    }
}
