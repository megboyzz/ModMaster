package ru.mod.master.ui.components.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Computer
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ru.mod.master.ui.AsPainter
import ru.mod.master.ui.components.ContentCenteredContainer
import ru.mod.master.ui.components.screens.modassembly.AssemblesList
import ru.mod.master.ui.components.screens.modassembly.NewModAssemblyBanner
import ru.mod.master.ui.components.screens.modassembly.NoModAssemblyBanner
import ru.mod.master.ui.loadImageBitmapFromFile
import ru.mod.master.ui.model.ModAssemblyModel
import ru.mod.master.ui.rememberTabOptions
import java.io.File
import java.time.LocalDate

object ModAssemblyTab : Tab {

    override val options: TabOptions
        @Composable
        get() = rememberTabOptions(
            index = 0u,
            title = "Сборки модов"
        )

    @Composable
    override fun Content() {



    }
}