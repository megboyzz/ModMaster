package ru.runner.validation

// Объект для валидации папок игры
class Validator(val pathToGame: String) {

    fun isGameFolder(): ValidationResult {
        return ValidationResult.ValidationSuccess()
    }

    fun isCleoInstalled(): ValidationResult {
        return ValidationResult.ValidationSuccess()
    }

    fun isModloaderInstaled(): ValidationResult {
        return ValidationResult.ValidationSuccess()
    }

    fun isGameDataBaseValid(): ValidationResult {
        return ValidationResult.ValidationSuccess()
    }

    fun isGameValid() : ValidationResult {

        val allResults = listOf(
            isGameFolder(),
            isCleoInstalled(),
            isModloaderInstaled(),
            isGameDataBaseValid()
        )

        // Проверка на наличие ошибок
        val hasErrors = allResults.any { it is ValidationResult.ValidationError }

        return if (hasErrors) {
            // Собираем все сообщения об ошибках
            val errorMessages = allResults.filterIsInstance<ValidationResult.ValidationError>()
                .joinToString(separator = "\n") { it.message.orEmpty() }
            ValidationResult.ValidationError(errorMessages)
        } else {
            ValidationResult.ValidationSuccess()
        }
    }

}