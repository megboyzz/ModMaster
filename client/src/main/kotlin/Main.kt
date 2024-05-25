import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import ru.mod.master.config.WindowConfig
import ru.mod.master.ui.components.AddButton
import ru.mod.master.ui.components.MainScaffold
import ru.mod.master.ui.components.TabNavigationItem
import ru.mod.master.ui.components.TwoActionFloatingButton
import ru.mod.master.ui.components.tabs.ModAssemblyTab
import ru.mod.master.ui.components.tabs.ModsTab
import ru.mod.master.ui.components.tabs.SettingsTab

fun main() = WindowConfig {
    TabNavigator(ModAssemblyTab) { tabNavigator ->

        MainScaffold(
            floatingButtonActionContent = {
                if(tabNavigator.current == ModAssemblyTab)
                    TwoActionFloatingButton(
                        onMainAction = {},
                        onSecondaryAction = {}
                    )
            },
            topBarContent = {
                TabNavigationItem(ModAssemblyTab)
                TabNavigationItem(ModsTab)
                TabNavigationItem(SettingsTab)
            }
        ) { CurrentTab() }
    }
}

