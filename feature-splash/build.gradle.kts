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
        implementation(projects.navigation)
        implementation(projects.dataPreferences)
    }
}
