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
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.core)
        implementation(platform(libs.supabase.bom))
        implementation(libs.supabase.postgrest)
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.serialization.kotlinx.json)

        implementation(projects.dataModel)
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }
    }
}

buildkonfig {
    packageName = libs.versions.namespace.get() + ".network"

    val secrets = Properties()
    val secretsFile = rootProject.file("config/secrets.properties")
    if (secretsFile.exists()) {
        secretsFile.inputStream().use { secrets.load(it) }
    }

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
