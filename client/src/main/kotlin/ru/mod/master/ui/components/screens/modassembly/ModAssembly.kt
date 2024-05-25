package ru.mod.master.ui.components.screens.modassembly

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Publish
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import ru.mod.master.ui.AsPainter
import ru.mod.master.ui.components.BaseMenuCard
import ru.mod.master.ui.components.MenuCardHeader
import ru.mod.master.ui.format
import ru.mod.master.ui.model.ModAssemblyModel
import java.time.LocalDate

@Composable
fun ModAssembly(
    modAssemblyModel: ModAssemblyModel,
    onClick: () -> Unit,
    onRemove: () -> Unit,
    onActivate: () -> Unit,
    onDeactivate: () -> Unit
) {

    BaseMenuCard(onClick = onClick) {
        ModAssemblyHeader(modAssemblyModel)
        ModAssemblyButtons(
            isActive = modAssemblyModel.isActive,
            onRemove = onRemove,
            onActivate = onActivate,
            onDeactivate = onDeactivate
        )
    }

}

@Composable
fun ModAssemblyHeader(modAssemblyModel: ModAssemblyModel) {

    val title = remember {
        val count = if(modAssemblyModel.size != null) "(${modAssemblyModel.size})" else ""
        "${modAssemblyModel.title}$count"
    }

    val date = remember {
        "Дата создания: ${modAssemblyModel.dateOfCreation.format("dd.MM.yyyy")}"
    }

    MenuCardHeader(
        painter = modAssemblyModel.icon ?: Icons.Default.QuestionMark.AsPainter(),
        title = title,
        subTitle = date
    )

}

@Composable
fun ModAssemblyButtons(
    isActive: Boolean,
    onRemove: () -> Unit,
    onActivate: () -> Unit,
    onDeactivate: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        if(isActive) {
            DeactivateButton(onDeactivate)
        } else {
            RemoveButton(onRemove)
            ActiveButton(onActivate)
        }
    }
}

@Composable
fun DeactivateButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null
        )
    }
}

@Composable
fun RemoveButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Outlined.Delete,
            contentDescription = null
        )
    }
}

@Composable
fun ActiveButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Outlined.Upload,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ModAssemblyHeaderPrev() {
    ModAssemblyHeader(
        modAssemblyModel = ModAssemblyModel(
            title = "My cool mods",
            isActive = true,
            dateOfCreation = LocalDate.now(),
            icon = null,
            size = null
        )
    )
}

@Preview
@Composable
fun ModAssemblyPrev() {

    ModAssembly(
        modAssemblyModel = ModAssemblyModel(
            title = "My cool mods",
            isActive = false,
            dateOfCreation = LocalDate.now(),
            icon = null,
            size = null
        ),
        onClick = {},
        onRemove = {},
        onDeactivate = {},
        onActivate = {}
    )

}

