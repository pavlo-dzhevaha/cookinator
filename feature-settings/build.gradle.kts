
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    android {
        namespace = libs.versions.namespace.get() + ".feature.settings"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.compose.runtime)
        implementation(libs.compose.foundation)
        implementation(libs.compose.ui)
        implementation(libs.compose.material3)
        implementation(libs.compose.material3.adaptive.navigation3)
        implementation(libs.compose.components.resources)
        implementation(libs.compose.uiToolingPreview)
        implementation(libs.androidx.lifecycle.viewmodelCompose)
        implementation(libs.androidx.lifecycle.runtimeCompose)
        implementation(libs.androidx.lifecycle.viewmodel.navigation3)
        implementation(libs.androidx.navigation3.ui)
        implementation(libs.kotlinx.datetime)
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.compose)
        implementation(libs.koin.compose.viewmodel)

        implementation(projects.navigation)
        implementation(projects.coreUi)
        implementation(projects.localisation)
        implementation(projects.dataPreferences)
    }

    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.time.ExperimentalTime")
            }
        }
    }
}

compose {
    resources {
        generateResClass = never
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}
