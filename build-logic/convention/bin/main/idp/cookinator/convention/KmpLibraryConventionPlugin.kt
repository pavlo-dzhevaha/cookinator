package idp.cookinator.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.android.kotlin.multiplatform.library")

        extensions.create("cookinator", CookinatorExtension::class.java).apply {
            namespaceSuffix = defaultNamespaceSuffix()
            androidResources = defaultAndroidResources()
        }

        plugins.withId("org.jetbrains.kotlin.multiplatform") {
            extensions.configure(KotlinMultiplatformExtension::class.java) {
                jvm()

                val cookinatorExt = this@with.extensions.getByType(CookinatorExtension::class.java)
                val suffix = cookinatorExt.namespaceSuffix.ifBlank { defaultNamespaceSuffix() }

                configureAndroidLibrary(
                    namespace = libs.version("namespace") + suffix,
                    compileSdk = libs.version("android-compileSdk").toInt(),
                    minSdk = libs.version("android-minSdk").toInt(),
                    enableAndroidResources = cookinatorExt.androidResources,
                )
            }
        }
    }
}
