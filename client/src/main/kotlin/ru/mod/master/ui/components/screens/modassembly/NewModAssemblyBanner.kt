package ru.mod.master.ui.components.screens.modassembly

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.mod.master.ui.components.CenteredDialogCard
import ru.mod.master.ui.components.CustomTextField
import ru.mod.master.ui.theme.H1
import ru.mod.master.ui.theme.accent

@Composable
fun NewModAssemblyBanner(
    onSaveClick: (String) -> Unit
) {

    var value by remember { mutableStateOf("") }

    CenteredDialogCard {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxHeight().width(300.dp)
            ) {
                Text(
                    text = "Создание соборки",
                    style = H1
                )
                Text("Введите имя новой сборки")
                CustomTextField(
                    value = value,
                    onValueChange = { value = it }
                )
                Button(
                    onClick = { onSaveClick(value) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = accent
                    )
                ) {
                    Text(
                        text = "Создать сборку",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewModAssemblyPrev() {
    NewModAssemblyBanner {  }
}

