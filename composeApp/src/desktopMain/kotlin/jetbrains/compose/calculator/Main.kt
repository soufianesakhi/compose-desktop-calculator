package jetbrains.compose.calculator

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import jetbrains.compose.calculator.resources.Assets
import org.jetbrains.compose.resources.painterResource

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
