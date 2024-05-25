package ru.mod.master.ui.components.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ru.mod.master.ui.components.screens.settings.*
import ru.mod.master.ui.rememberTabOptions

object SettingsTab : Tab {

    private fun readResolve(): Any = SettingsTab

    override val options: TabOptions
        @Composable
        get() = rememberTabOptions(
            index = 2u,
            title = "Настройки"
        )

    @Composable
    override fun Content() {

        var path by remember { mutableStateOf("") }
        var repo by remember { mutableStateOf("") }
        var listOfRepos = remember { mutableListOf<String>() }

        var fileDialogIsOpened by remember { mutableStateOf(false) }

        if(fileDialogIsOpened)
            FileChooserDialog(
                title = "hahah",
                onResult = {
                    path = it.absolutePath
                    fileDialogIsOpened = false
                },
                onCancel = {
                    fileDialogIsOpened = false
                }
            )

        ContentBox {

            Text("Путь к игре с установленным modloader")
            TextFieldWithButton(
                textValue = path,
                buttonIcon = Icons.Filled.Folder,
                onButtonClick = { fileDialogIsOpened = !fileDialogIsOpened }
            )

            Text("URL репозитория модов")
            TextFieldWithButton(
                textValue = repo,
                buttonIcon = Icons.Filled.Add,
                onTextValueChanged = { repo = it },
                onButtonClick = {
                    //TODO проверки на валидность
                    listOfRepos.add(repo)
                    repo = ""
                }
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = listOfRepos,
                ) {
                    RepositoryShield(it) {

                        val copy = listOfRepos.map { str -> str }.toMutableList()
                        copy.remove(it)

                        listOfRepos = copy

                        println("Remove!")
                    }
                }
            }

            SaveButton {

            }


        }

    }
}