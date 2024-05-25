package ru.mod.master.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import ru.mod.master.ui.theme.*
import java.util.*
import kotlin.concurrent.schedule

// В одном файле не более 3-х функций Composable

@Composable
fun MainTopBar(
    topBarContent: @Composable RowScope.() -> Unit
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
                content = topBarContent
            )
        },
        backgroundColor = mainColor
    )
}

@Composable
fun AddButton(
    //onHoverClick: () -> Unit,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = accent
    ){
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = mainColor,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun TwoActionFloatingButton(
    onMainAction: () -> Unit,
    onSecondaryAction: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val offset by animateDpAsState(targetValue = if (expanded) 72.dp else 0.dp)

    //Основаная кнопка
    FloatingActionButton(
        onClick = onMainAction,
        modifier = Modifier.offset(y = -offset * 2),
        backgroundColor = accent
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = mainColor,
        )
    }

    FloatingActionButton(
        onClick = onSecondaryAction,
        modifier = Modifier.offset(y = -offset),
        backgroundColor = accent
    ) {
        Icon(
            imageVector = Icons.Filled.OpenInNew,
            contentDescription = null,
            tint = mainColor,
        )
    }


    //Кнопка для включения
    FloatingActionButton(
        onClick = { expanded =! expanded },
        backgroundColor = accent
    ){
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = mainColor,
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToolTipForContent(
    titleOfTooltip: String,
    offset: DpOffset,
    content: @Composable () -> Unit
) {
    TooltipArea(
        tooltip = {
            Surface(
                modifier = Modifier.shadow(4.dp),
                color = Color(255, 255, 210),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = titleOfTooltip,
                    modifier = Modifier.padding(10.dp)
                )
            }
        },
        //modifier = Modifier.padding(start = 40.dp),
        delayMillis = 600, // in milliseconds
        tooltipPlacement = TooltipPlacement.ComponentRect(offset = offset),
        content = content
    )
}


@Composable
fun MainScaffold(
    floatingButtonActionContent: @Composable () -> Unit,
    topBarContent: @Composable RowScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        backgroundColor = background,
        topBar = { MainTopBar(topBarContent) },
        //TODO ADD BUTTON
        floatingActionButton = floatingButtonActionContent,
        content = content
    )
}

