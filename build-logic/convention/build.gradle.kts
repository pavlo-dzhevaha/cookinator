plugins {
    `kotlin-dsl`
}

group = "idp.cookinator.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.gradle.api)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = "cookinator.kmp.library"
            implementationClass = "idp.cookinator.convention.KmpLibraryConventionPlugin"
        }
        register("compose") {
            id = "cookinator.compose"
            implementationClass = "idp.cookinator.convention.ComposeConventionPlugin"
        }
        register("feature") {
            id = "cookinator.feature"
            implementationClass = "idp.cookinator.convention.FeatureConventionPlugin"
        }
    }
}
