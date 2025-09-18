package jetbrains.compose.calculator

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import composeResources.Res
import composeResources.app_name
import composeResources.calculator
import jetbrains.compose.calculator.resources.Icons
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        title = stringResource(Res.string.app_name),
        state = rememberWindowState(
            width = 400.dp,
            height = 500.dp,
            position = WindowPosition.Aligned(Alignment.Center)
        ),
        icon = painterResource(Icons.calculator),
        onCloseRequest = ::exitApplication) {
        App()
    }
}
