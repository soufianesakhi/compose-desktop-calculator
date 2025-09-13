package jetbrains.compose.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import jetbrains.compose.calculator.resources.lightThemeColors
import jetbrains.compose.calculator.view.DisplayPanel
import jetbrains.compose.calculator.view.Keyboard

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
