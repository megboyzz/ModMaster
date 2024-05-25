package ru.mod.master.ui.components.tabs

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ru.mod.master.ui.components.BlankText
import ru.mod.master.ui.components.screens.mods.ModCardPrev
import ru.mod.master.ui.rememberTabOptions

object ModsTab : Tab {

    override val options: TabOptions
        @Composable
        get() = rememberTabOptions(
            index = 1u,
            title = "Моды"
        )

    @Composable
    override fun Content() {
        ModCardPrev()
    }
}