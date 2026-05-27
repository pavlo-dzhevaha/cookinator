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
        // Koin
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.compose)
        // Supabase
        implementation(platform(libs.supabase.bom))
        implementation(libs.supabase.postgrest)
        // Core Ktor Client
        implementation(libs.ktor.client.core)
        // JSON Serialization
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.serialization.kotlinx.json)

        implementation(projects.dataModel)
    }

    sourceSets {
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
    val secrets = Properties()
    val secretsFile = rootProject.file("config/secrets.properties")
    if (secretsFile.exists()) {
        secretsFile.inputStream().use { secrets.load(it) }
    }

    // 2. Map the property to a Kotlin constant
    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING,
            "API_KEY",
            secrets.getProperty("SPOONACULAR_API_KEY") ?: "",
        )
        buildConfigField(
            FieldSpec.Type.STRING,
            "SUPABASE_URL",
            secrets.getProperty("SUPABASE_URL") ?: "",
        )
        buildConfigField(
            FieldSpec.Type.STRING,
            "SUPABASE_ANON_KEY",
            secrets.getProperty("SUPABASE_ANON_KEY") ?: "",
        )
    }
}
