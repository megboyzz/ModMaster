pluginManagement {

    val kotlinVersion: String by settings
    val composeVersion: String by settings
    val ktorVersion: String by settings

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(url = "https://mvn.mchv.eu/repository/mchv/")
    }

    plugins {
        kotlin("jvm") version kotlinVersion
        id("org.jetbrains.compose") version composeVersion
        id("io.ktor.plugin") version ktorVersion
    }
}

rootProject.name = "multi_one"

include("server")
include("client")
include("runner")
include("runner")
