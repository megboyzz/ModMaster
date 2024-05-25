package ru.mod.master.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import ru.mod.master.ui.theme.H1
import ru.mod.master.ui.theme.accent
import ru.mod.master.ui.theme.accentAction

@Composable
fun ScaffoldTab(
    title: String,
    active: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = title,
                style = H1,
            )
        }
        Box(
            modifier = Modifier
                .height(5.dp)
                .fillMaxWidth()
                .background(if(active) accentAction else accent),
        ) {}
    }
}

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    ScaffoldTab(
        title = tab.options.title,
        active = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
    )
}