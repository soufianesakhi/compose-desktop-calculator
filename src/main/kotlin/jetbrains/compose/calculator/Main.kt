package jetbrains.compose.calculator

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import jetbrains.compose.calculator.resources.lightThemeColors
import jetbrains.compose.calculator.view.DisplayPanel
import jetbrains.compose.calculator.view.Keyboard

const val DEFAULT_WIDTH = 500
const val DEFAULT_HEIGHT = 500

fun main() = Window(title = "Compose Calculator - simply-how.com", size = IntSize(DEFAULT_WIDTH, DEFAULT_HEIGHT)) {
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
