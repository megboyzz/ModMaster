package ru.mod.master

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import ru.mod.master.ui.AsPainter
import ru.mod.master.ui.components.BaseMenuCard
import ru.mod.master.ui.components.ContentCenteredContainer
import ru.mod.master.ui.components.screens.mods.ModReplace
import ru.mod.master.ui.loadImageBitmapFromFile
import ru.mod.master.ui.model.ModModel
import ru.mod.master.ui.model.StockItemModel
import java.io.File
import kotlin.random.Random

@Composable
fun Content() = BaseMenuCard(enabled = false, onClick = {}) {}

const val imageDirectory = "D:\\Projects\\cars"

fun getRandomFileFromDirectory(directoryPath: String): File? {
    val directory = File(directoryPath)
    val files = directory.listFiles().filter { it.isFile }
    return files.random()
}

// Генерируем случайные данные для списков
fun generateRandomModModels(count: Int): List<ModModel> {
    val modModels = mutableListOf<ModModel>()
    val titles = listOf("BMW", "Toyota", "Honda", "Ford", "Chevrolet") // Примеры заголовков
    val subtitles = listOf("Sport", "Luxury", "SUV", "Sedan", "Hatchback") // Примеры подзаголовков

    repeat(count) {
        val randomTitle = titles.random()
        val randomSubtitle = subtitles.random()
        val randomImageFile = getRandomFileFromDirectory(imageDirectory)
        val randomIcon = randomImageFile?.let { loadImageBitmapFromFile(it).AsPainter() }

        val modModel = ModModel(
            icon = randomIcon,
            title = randomTitle,
            subtitle = randomSubtitle,
            isActive = Random.nextBoolean()
        )
        modModels.add(modModel)
    }
    return modModels
}

fun generateRandomStockItemModels(count: Int): List<StockItemModel> {
    val stockItemModels = mutableListOf<StockItemModel>()
    val titles = listOf("Admiral", "Commander", "Captain", "Navigator", "Explorer") // Примеры заголовков

    repeat(count) {
        val randomTitle = titles.random()
        val randomImageFile = getRandomFileFromDirectory(imageDirectory)
        val randomIcon = randomImageFile?.let { loadImageBitmapFromFile(it).AsPainter() }

        val stockItemModel = StockItemModel(
            icon = randomIcon,
            title = randomTitle
        )
        stockItemModels.add(stockItemModel)
    }
    return stockItemModels
}


fun main() = singleWindowApplication {

    val mods = generateRandomModModels(10)
    val stocks = generateRandomStockItemModels(10)

    val content = mods.zip(stocks).toList()

    ContentCenteredContainer {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(content) { index: Int, item: Pair<ModModel, StockItemModel> ->
                ModReplace(
                    modOrderNumber = index.toShort(),
                    modModel = item.first,
                    stockItemModel = item.second,
                    onEditModClick = {},
                    onRemoveModReplaceClick = {},
                    onEditStockItemModelClick = {}
                )
            }
        }
    }

}