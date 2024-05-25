package ru.mod.master.ui.components.screens.modassembly

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.mod.master.ui.model.ModAssemblyModel
import ru.mod.master.ui.theme.H1
import ru.mod.master.ui.theme.accent
import ru.mod.master.ui.theme.mainColor

@Composable
fun AssembliesTitle(text: String) {
    Text(
        text = text,
        style = H1,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AssemblesList(
    listOfAssemblyModel: List<ModAssemblyModel>,
    onRemove: (ModAssemblyModel) -> Unit,
    onActivate: (ModAssemblyModel) -> Unit,
    onDeactivate: (ModAssemblyModel) -> Unit
) {

    val active = remember {
        listOfAssemblyModel.filter { it.isActive }
    }
    val notActive = remember {
        listOfAssemblyModel.filter { !it.isActive }
    }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 28.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        userScrollEnabled = true
    ) {
        item {
            if(active.isEmpty())
                AssembliesTitle("Нет активных сборок")
            else
                AssembliesTitle("Активная сборка")
        }
        items(active) {
            ModAssembly(
                modAssemblyModel = it,
                onClick = {},
                onRemove = { onRemove(it) },
                onDeactivate = { onDeactivate(it) },
                onActivate = { onActivate(it) }
            )
        }
        item {
            if(notActive.isEmpty())
                AssembliesTitle("Нет доступных сборок")
            else
                AssembliesTitle("Доступные сборки")
        }
        items(notActive) {
            ModAssembly(
                modAssemblyModel = it,
                onClick = {},
                onRemove = { onRemove(it) },
                onDeactivate = { onDeactivate(it) },
                onActivate = { onActivate(it) }
            )
        }
    }
}
