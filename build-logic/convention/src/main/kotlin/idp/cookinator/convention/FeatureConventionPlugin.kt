package idp.cookinator.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("cookinator.kmp.library")
        pluginManager.apply("cookinator.compose")

        afterEvaluate {
            extensions.configure(KotlinMultiplatformExtension::class.java) {
                sourceSets.named("commonMain").configure {
                    dependencies {
                        implementation(libs.library("compose-runtime"))
                        implementation(libs.library("compose-foundation"))
                        implementation(libs.library("compose-ui"))
                        implementation(libs.library("compose-material3"))
                        implementation(libs.library("compose-uiToolingPreview"))
                        val koinBom = project.dependencies.platform(libs.library("koin-bom").get())
                        implementation(koinBom)
                        implementation(libs.library("koin-compose"))
                        implementation(libs.library("koin-compose-viewmodel"))
                    }
                }
            }
        }
    }
}
