import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildkonfig)
}

kotlin {
    android {
        namespace = libs.versions.namespace.get() + ".network"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.kotlinx.datetime)
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.compose)
    }

    sourceSets {
        commonMain.dependencies {
            // Core Ktor Client
            implementation(libs.ktor.client.core)

            // JSON Serialization
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        androidMain.dependencies {
            // Android Engine
            implementation(libs.ktor.client.okhttp)
        }

        jvmMain.dependencies {
            // Desktop Engine
            implementation(libs.ktor.client.cio)
        }

        all {
            languageSettings {
                optIn("kotlin.time.ExperimentalTime")
            }
        }
    }
}

buildkonfig {
    packageName = libs.versions.namespace.get() + ".network"

    // 1. Read the local.properties file
    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("config/secrets.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { localProperties.load(it) }
    }

    // 2. Map the property to a Kotlin constant
    defaultConfigs {
        val apiKey = localProperties.getProperty("SPOONACULAR_API_KEY") ?: ""
        buildConfigField(FieldSpec.Type.STRING, "API_KEY", apiKey)
    }
}
