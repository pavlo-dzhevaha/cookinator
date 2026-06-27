package idp.cookinator.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeAndroidTooling() {
    extensions.configure(KotlinMultiplatformExtension::class.java) {
        sourceSets.named("androidMain").configure {
            dependencies {
                implementation(libs.library("compose-uiTooling"))
            }
        }
    }
}
