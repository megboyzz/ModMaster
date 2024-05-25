plugins {
    kotlin("multiplatform") version "1.9.22" apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(url = "https://mvn.mchv.eu/repository/mchv/")
    }
}

// ":server:runDocker"
// ":client:runRelease"

tasks.register("runAll") {
    dependsOn(":server:runDocker", ":client:runRelease")

}

