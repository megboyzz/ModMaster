package ru.mod.master.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import jdk.jfr.Enabled
import ru.mod.master.ui.theme.blackInactive
import ru.mod.master.ui.theme.mainColor
import java.awt.Image

//Общие компонениты которые стоит переиспользовать

@Composable
fun BlankText(text: String = "Blank") {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { Text(text) }
}

@Composable
fun CustomTextField(
    hint: String? = null,
    trailingIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
) {

    val shape = remember { RoundedCornerShape(8.dp) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            decorationBox = { innerTextField ->
                Card(
                    shape = shape,
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 0.5.dp,
                            color = Color.Black,
                            shape = shape
                        )

                ) {
                    Row(
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        innerTextField()
                        trailingIcon()
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseMenuCard(
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Card(
        enabled = enabled,
        border = if(enabled) null else BorderStroke(0.1.dp, Color.Black),
        onClick = onClick,
        elevation = if(enabled) 4.dp else 0.dp,
        shape = RoundedCornerShape(12.dp),
        content = {
            Box{
                if(!enabled)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(blackInactive)
                    ) {
                        BaseMenuCardContainer(content)
                    }
                else
                    BaseMenuCardContainer(content)
            }
        }
    )
}

@Composable
fun BaseMenuCardContainer(
    content: @Composable RowScope.() -> Unit
) {
    Box(Modifier.padding(20.dp).fillMaxWidth()) {
        Row(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        )
    }
}

@Composable
fun MenuCardHeader(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    subTitle: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageForPainter(painter)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = title)
            Text(text = subTitle)
        }
    }
}

@Composable
fun ImageForPainter(
    painter: Painter,
    isBorder: Boolean = true
) {

    val modifier = remember(isBorder) {
        if(isBorder)
            Modifier.border(
                width = 0.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
        else
            Modifier
    }

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(10.dp))
            .then(modifier)
    )
}

@Composable
fun ContentCenteredContainer(
    content: @Composable ColumnScope.() -> Unit
) {

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(700.dp),
            content = content
        )
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BaseDialogCardContainer(
    content: @Composable BoxScope.() -> Unit
) {
    val containerSize = LocalWindowInfo.current.containerSize

    val width = remember {
        val widthCoeff = 0.35f
        containerSize.width * widthCoeff
    }
    val height = remember {
        val heightCoeff = 0.3f
        containerSize.height * heightCoeff
    }

    Card(
        backgroundColor = mainColor,
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.size(width.dp, height.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            content = content
        )

    }
}

@Composable
fun CenteredDialogCard(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = { BaseDialogCardContainer(content) }
    )
}

@Composable
fun ShieldContextMenuContext(
    onRemove: () -> Unit,
    shield: @Composable () -> Unit
) {
    ContextMenuArea(
        items = {
            listOf(ContextMenuItem("Удалить", onClick = onRemove))
        },
        content = shield
    )
}
