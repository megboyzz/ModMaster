package ru.mod.master.ui.components.screens.mods

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mod.master.ui.AsPainter
import ru.mod.master.ui.components.*
import ru.mod.master.ui.loadImageBitmapFromFile
import ru.mod.master.ui.model.ModModel
import java.io.File
import kotlin.random.Random

@Composable
fun ModCard(
    modModel: ModModel,
    onClick: () -> Unit
) {
    BaseMenuCard(
        onClick = onClick,
        enabled = modModel.isActive
    ) {
        BaseMenuCardContainer {
            MenuCardHeader(
                painter = modModel.icon ?: Icons.Default.QuestionMark.AsPainter(),
                title = modModel.title,
                subTitle = modModel.subtitle
            )
            Icon(
                painter = Icons.Default.ChevronRight.AsPainter(),
                modifier = Modifier.size(32.dp),
                contentDescription = null
            )
        }
    }
}

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit
) {
    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = Icons.Default.Search.AsPainter(),
                    contentDescription = null
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchAndViewModsHolder(
    value: String,
    onValueChange: (String) -> Unit,
    list: List<ModModel>,
    onClickMod: (ModModel) -> Unit
) {

    LazyColumn(
        contentPadding = PaddingValues(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        userScrollEnabled = true
    ) {
        stickyHeader {
            Spacer(Modifier.height(28.dp))
            SearchField(
                value = value,
                onValueChange = onValueChange
            )
        }

        items(list.filter { it.title.contains(value) }) {
            ModCard(
                modModel = it,
                onClick = { onClickMod(it) }
            )
        }
    }
}

@Preview
@Composable
fun ModCardPrev() {

    val icons = listOf(
        Icons.Default.Search.AsPainter(),
        Icons.Default.Egg.AsPainter(),
        Icons.Default.Escalator.AsPainter(),
        Icons.Default.Folder.AsPainter(),
        Icons.Default.CarRepair.AsPainter(),
        loadImageBitmapFromFile(File("C:\\Users\\ikits\\Downloads\\110335-1298289725-gta-sa-2011-02-21-14-59-13-89.jpg")).AsPainter()
    )

    val titles = listOf("BMW M3 GTR", "Audi R8", "Mercedes AMG GT", "Porsche 911", "Lamborghini Huracan")
    val subtitles = listOf("Не активен", "Активен", "В ремонте", "Продан", "В аренде")

    val list = List(10) { index ->
        ModModel(
            icon = icons[index % icons.size],
            title = titles[index % titles.size],
            subtitle = subtitles[index % subtitles.size],
            isActive = Random.nextBoolean()
        )
    }

    var value by remember { mutableStateOf("") }

    ContentCenteredContainer {

        SearchAndViewModsHolder(
            value = value,
            onValueChange = {value = it},
            list = list,
            onClickMod = {}
        )
    }
}