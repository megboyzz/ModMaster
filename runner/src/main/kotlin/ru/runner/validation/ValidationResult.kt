package ru.runner.validation

import ru.runner.models.ModManifest
import java.io.File


sealed class ValidationResult(
    val message: String? = null
) {
    class ValidationSuccess(message: String? = null) : ValidationResult(message)
    class ValidationError(message: String) : ValidationResult(message)
    class Validating : ValidationResult("Loading...")
}

interface Installation {

    fun onPreInstall()

    fun doInstall(
        modManifest: ModManifest,
        gameFolders: GameFolders
    ): Boolean

    fun onPostInstall()

}

interface GameFolders {

    val cleoFolder: File

    val carsReplaceFolder: File

    val modsFolder: File

}