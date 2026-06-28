@file:Suppress("UnstableApiUsage")

rootProject.name = "Cookinator"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":androidApp")
include(":desktopApp")
include(":host")
include(":localisation")
include(":navigation")
include(":core-ui")
include(":core-logging")
include(":data-preferences")
include(":data-network")
include(":data-database")
include(":data-model")
include(":domain")
include(":notification")
include(":feature-splash")
include(":feature-onboarding")
include(":feature-main")
include(":feature-settings")
include(":feature-recipe")
include(":feature-all-recipes")
include(":feature-create-recipe")
