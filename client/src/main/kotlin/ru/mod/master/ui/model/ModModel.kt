package ru.mod.master.ui.model

import androidx.compose.ui.graphics.painter.Painter

data class ModModel(
    val icon: Painter? = null,
    val title: String,
    val subtitle: String,
    val isActive: Boolean,
)
