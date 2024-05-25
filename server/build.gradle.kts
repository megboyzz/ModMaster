
val kotlinVersion: String by project

plugins {
    kotlin("jvm")
    id("io.ktor.plugin")
}

group = "ru.megboyzz"
version = "0.0.1"

application {
    mainClass.set("ru.megboyzz.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

ktor {
    docker {
        localImageName.set("server-docker-image")
        imageTag.set("0.0.1")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    //implementation("ch.qos.logback:logback-classic:1.2.13")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    //testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
