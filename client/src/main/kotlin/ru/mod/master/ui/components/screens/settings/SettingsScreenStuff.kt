package ru.mod.master.ui.components.screens.settings

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.mod.master.ui.theme.H2
import ru.mod.master.ui.theme.accent
import ru.mod.master.ui.theme.mainColor
import java.io.File
import java.net.URL
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView
import ru.mod.master.ui.components.CustomTextField
import ru.mod.master.ui.components.ShieldContextMenuContext


@Composable
fun ContentBox(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {

    Box(
        modifier = Modifier.padding(32.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier,
            content = content
        )
    }

}

@Composable
fun ButtonInSettings(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = mainColor
        ),
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun TextFieldWithButton(
    textValue: String,
    onTextValueChanged: (String) -> Unit = {},
    buttonIcon: ImageVector,
    onButtonClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CustomTextField(
            modifier = Modifier.weight(1f),
            value = textValue,
            onValueChange = onTextValueChanged
        )
        ButtonInSettings(
            modifier = Modifier.weight(0.1f),
            icon = buttonIcon,
            onClick = onButtonClick
        )
    }
}

@Composable
fun RepositoryShield(
    repoUrl: String,
    onRemove: () -> Unit,
) = ShieldContextMenuContext(onRemove) {
    RepositoryShieldBase(repoUrl)
}

@Composable
fun RepositoryShieldBase(
    repoUrl: String
) {
    val repo = remember {
        val url = runCatching {
            URL(repoUrl)
        }.getOrElse { return@remember repoUrl }
        val protocol = url.protocol
        repoUrl.replace("$protocol://", "")
    }
    Card(
        elevation = 4.dp,
        backgroundColor = accent,
        shape = RoundedCornerShape(8.dp)
    ){
        Box(
            modifier = Modifier.padding(4.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                //modifier = Modifier.padding(4.dp),
                text = repo,
                style = H2,
                color = mainColor
            )
        }
    }
}

@Composable
fun SaveButton(
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = mainColor
            ),
            onClick = onClick
        ) {
            Text("Сохранить")
        }
    }
}

@Composable
fun FileChooserDialog(
    title: String,
    onCancel: () -> Unit,
    onResult: (result: File) -> Unit,
) {
    val fileChooser = JFileChooser(FileSystemView.getFileSystemView())
    fileChooser.currentDirectory = File(System.getProperty("user.dir"))
    fileChooser.dialogTitle = title
    fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
    fileChooser.isAcceptAllFileFilterUsed = true
    fileChooser.selectedFile = null
    fileChooser.currentDirectory = null

    val dialog = fileChooser.showOpenDialog(null)

    when(dialog) {
        JFileChooser.APPROVE_OPTION -> onResult(fileChooser.selectedFile)
        JFileChooser.CANCEL_OPTION -> onCancel()
        else -> onCancel()
    }
}