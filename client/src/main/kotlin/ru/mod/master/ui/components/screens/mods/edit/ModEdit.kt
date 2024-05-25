package ru.mod.master.ui.components.screens.mods.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ContentBox(
    content: @Composable () -> Unit
) {
    Box(Modifier.size(260.dp, 130.dp)) {
        Column { content() }
    }
}
