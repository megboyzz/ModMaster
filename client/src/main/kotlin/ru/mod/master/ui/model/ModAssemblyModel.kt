package ru.mod.master.ui.model

import androidx.compose.ui.graphics.painter.Painter
import java.time.LocalDate

data class ModAssemblyModel(
    val title: String,
    val isActive: Boolean,
    val dateOfCreation: LocalDate,
    val icon: Painter? = null,
    val size: Int? = null
)
