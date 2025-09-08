package jetbrains.compose.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import jetbrains.compose.calculator.resources.Assets
import jetbrains.compose.calculator.resources.lightThemeColors
import jetbrains.compose.calculator.view.DisplayPanel
import jetbrains.compose.calculator.view.Keyboard
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
        MaterialTheme(colors = lightThemeColors) {
            val mainOutput = remember { mutableStateOf(TextFieldValue("0")) }
            Column(Modifier.fillMaxHeight()) {
                DisplayPanel(
                    Modifier.weight(1f),
                    mainOutput
                )
                Keyboard(
                    Modifier.weight(4f),
                    mainOutput
                )
            }
        }
}

fun main() = application {
    Window(
        title = "Calculator",
        state = rememberWindowState(
            width = 400.dp,
            height = 500.dp,
            position = WindowPosition.Aligned(Alignment.Center)
        ),
        icon = painterResource(Assets.WindowIcon),
        onCloseRequest = ::exitApplication) {
        App()
    }
}
