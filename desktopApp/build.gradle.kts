import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    dependencies {
        implementation(compose.desktop.currentOs)
        implementation(libs.kotlinx.coroutinesSwing)
        implementation(libs.compose.components.resources)
        implementation(libs.kmp.notifier)

        implementation(projects.host)
        implementation(projects.localisation)
        implementation(projects.coreUi)
    }
}

compose {
    resources {
        generateResClass = never
    }
    desktop {
        application {
            mainClass = libs.versions.namespace.get() + ".MainKt"

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = libs.versions.namespace.get()
                packageVersion = libs.versions.version.get()
            }
        }
    }
}
