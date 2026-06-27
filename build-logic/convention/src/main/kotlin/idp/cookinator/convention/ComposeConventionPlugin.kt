package idp.cookinator.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.compose")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        extensions.create("composeCookinator", ComposeCookinatorExtension::class.java).apply {
            androidTooling = true
        }

        afterEvaluate {
            val extension = extensions.getByType(ComposeCookinatorExtension::class.java)
            if (extension.androidTooling) {
                configureComposeAndroidTooling()
            }
        }
    }
}
