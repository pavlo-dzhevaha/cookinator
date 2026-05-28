import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
}

kotlin {
    android {
        namespace = libs.versions.namespace.get() + ".notification"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.compose)
        implementation(libs.kmp.notifier)
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.workmanager)
        }
    }
}
