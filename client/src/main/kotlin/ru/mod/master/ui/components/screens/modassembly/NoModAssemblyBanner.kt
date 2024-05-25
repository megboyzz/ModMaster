package ru.mod.master.ui.components.screens.modassembly

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.mod.master.ui.components.BaseDialogCardContainer
import ru.mod.master.ui.components.CenteredDialogCard
import ru.mod.master.ui.theme.H1
import ru.mod.master.ui.theme.mainColor

@Composable
fun NoModAssemblyBanner(
    onClickCreate: () -> Unit,
    onClickLoad: () -> Unit
) {
    CenteredDialogCard {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Нет ни одной сборки",
                style = H1
            )
            OptionButtons(onClickCreate, onClickLoad)
        }
    }
}

@Composable
fun OptionButtons(
    onClickCreate: () -> Unit,
    onClickLoad: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icons.Filled.OpenWith
        OptionButton(
            title = "Загрузить сборку с диска",
            icon = Icons.Filled.OpenInNew,
            onClick = onClickLoad,
            modifier = Modifier.weight(1f)
        )
        Divider(
            modifier = Modifier.fillMaxHeight().width(1.dp),
            color = Color.Black
        )
        OptionButton(
            title = "Создать сборку",
            icon = Icons.Filled.AddCircle,
            onClick = onClickCreate,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OptionButton(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .onClick(onClick = onClick)
            .indication(interactionSource, rememberRipple())
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset: Offset ->
                        val press = PressInteraction.Press(offset)
                        interactionSource.emit(press)
                        // Waits for the press to be released before returning.
                        // If the press was released, true is returned, or if the gesture
                        // was canceled by motion being consumed by another gesture, false is returned.
                        tryAwaitRelease()
                        // We emit a release press interaction here
                        interactionSource.emit(PressInteraction.Release(press))

                    }
                )
            }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = title,
                textAlign = TextAlign.Center
            )
        }
    }

}