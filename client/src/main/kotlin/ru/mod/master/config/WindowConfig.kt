package ru.mod.master.config

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ru.mod.master.ui.AsPainter

fun WindowConfig(
    content: @Composable FrameWindowScope.() -> Unit
) = application {

    val scale = 70

    Window(
        icon = Icons.Default.Settings.AsPainter(),
        title = "ModMaster",
        onCloseRequest = ::exitApplication,
        resizable = false,
        state = WindowState(size = DpSize((scale * 16).dp, (scale * 9).dp)),
        content = content
    )
}