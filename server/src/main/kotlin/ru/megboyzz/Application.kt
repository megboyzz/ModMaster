package ru.megboyzz

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::configureRouting)
        .start(wait = true)
}

data class Package(val name: String, val image: String)

val packagesDirectory = File("/path/to/packages")

fun Application.configureRouting() {
    routing {
        get("/packages") {
            val packageList = packagesDirectory.listFiles()?.map { file ->
                Package(file.nameWithoutExtension, "/packages/${file.name}")
            } ?: emptyList()
            call.respond(packageList)
        }
        get("/packages/{name}") {
            val packageName = call.parameters["name"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val packageFile = File(packagesDirectory, "$packageName.zip")
            if (!packageFile.exists()) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                val packageInfo = Package(packageName, "/packages/$packageName")
                call.respond(packageInfo)
            }
        }
        get("/packages/{name}/download") {
            val packageName = call.parameters["name"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val packageFile = File(packagesDirectory, "$packageName.zip")
            if (!packageFile.exists()) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respondFile(packageFile)
            }
        }
        static("/packages") {
            // Serve packages directory as static content
            files(packagesDirectory)
        }
    }
}