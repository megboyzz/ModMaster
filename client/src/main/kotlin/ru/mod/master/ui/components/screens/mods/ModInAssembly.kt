package ru.mod.master.ui.components.screens.mods

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material.icons.outlined.SwapVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ru.mod.master.ui.AsPainter
import ru.mod.master.ui.components.CenteredDialogCard
import ru.mod.master.ui.components.ImageForPainter
import ru.mod.master.ui.components.ShieldContextMenuContext
import ru.mod.master.ui.loadImageBitmapFromFile
import ru.mod.master.ui.model.ModModel
import ru.mod.master.ui.model.StockItemModel
import ru.mod.master.ui.theme.H2
import ru.mod.master.ui.theme.accent
import ru.mod.master.ui.theme.textBox
import java.io.File

@Composable
fun ModReplace(
    modOrderNumber: Short,
    modModel: ModModel,
    stockItemModel: StockItemModel,
    onEditModClick: () -> Unit,
    onEditStockItemModelClick: () -> Unit,
    onRemoveModReplaceClick: () -> Unit,
) {
    ModBaseCard(
        onRemove = onRemoveModReplaceClick,
        layerUnderContent = {
            ModReplaceCountShield(modOrderNumber)
        },
    ){
        Row(
            modifier = Modifier
                .padding(top = 60.dp)
                .padding(bottom = 16.dp)
                .padding(horizontal = 8.dp)
                .height(150.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReplaceColumn(
                icon = modModel.icon,
                name = modModel.title,
                onEditClick = onEditModClick
            )
            Icon(
                imageVector = Icons.Outlined.SwapHoriz,
                contentDescription = null
            )
            ReplaceColumn(
                icon = stockItemModel.icon,
                name = stockItemModel.title,
                onEditClick = onEditStockItemModelClick
            )
        }
    }
}


@Composable
fun ModBaseCard(
    onRemove: () -> Unit,
    layerUnderContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    ShieldContextMenuContext(onRemove) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(
                width = 0.5.dp,
                color = accent
            ),
            elevation = 4.dp,
            shape = remember { RoundedCornerShape(20.dp) }
        ) {
            Box(Modifier.fillMaxSize()) {
                layerUnderContent()
                content()
            }
        }
    }
}

@Composable
fun ModReplaceCountShield(number: Short) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 20.dp))
                .background(accent)
                .size(60.dp, 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun ModReplPrev() {
    ModBaseCard(
        onRemove = {},
        layerUnderContent = {
            ModReplaceCountShield(1)
        },
        content = {
            CenteredDialogCard {  }
        }
    )
}

@Composable
fun ReplaceColumn(
    icon: Painter?,
    name: String,
    onEditClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight().width(120.dp)
    ) {
        ImageForPainter(
            painter = icon ?: Icons.Outlined.QuestionMark.AsPainter(),
            isBorder = false
        )
        Text(
            text = name,
        )
        ReplaceEditButton(onEditClick)
    }
}

@Composable
fun ReplaceEditButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .wrapContentSize()
            .size(40.dp),
        shape = remember { RoundedCornerShape(8.dp) },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = accent,
            contentColor = Color.White
        )
    ) {
        Icon(
            imageVector = Icons.Outlined.Edit,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ColumnTest() {

    val file = File("D:\\Projects\\cars\\admiral.jpg")

    ModReplace(
        modOrderNumber = 1,
        modModel = ModModel(
            icon = loadImageBitmapFromFile(file).AsPainter(),
            title = "BMW",
            subtitle = "",
            isActive = false
        ),
        stockItemModel = StockItemModel(
            icon = loadImageBitmapFromFile(file).AsPainter(),
            title = "Admiral"
        ),
        onEditModClick = {},
        onEditStockItemModelClick = {},
        onRemoveModReplaceClick = {}
    )
}
