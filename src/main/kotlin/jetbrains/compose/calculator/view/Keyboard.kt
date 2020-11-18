package jetbrains.compose.calculator.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetbrains.compose.calculator.resources.jostFontFamily

@Composable
fun Keyboard(
    modifier: Modifier,
    mainOutput: MutableState<TextFieldValue>
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background
    ) {
        KeyboardKeys(mainOutput)
    }
}

@Composable
fun KeyboardKeys(mainOutput: MutableState<TextFieldValue>) {
    Row(modifier = Modifier.fillMaxSize()) {
        KeyboardLayout.forEach { keyColumn ->
            Column(modifier = Modifier.weight(1f)) {
                keyColumn.forEach { key ->
                    KeyboardKey(Modifier.weight(1f), key, mainOutput)
                }
            }
        }
    }
}

@Composable
fun KeyboardKey(modifier: Modifier, key: Key?, mainOutput: MutableState<TextFieldValue>) {
    if (key == null) {
        return EmptyKeyView(modifier)
    }
    KeyView(modifier = modifier, onClick = key.onClick?.let {
        { it(mainOutput) }
    } ?: {
        val textValue = mainOutput.value.text.let {
            if (it == "0") key.value else it + key.value
        }
        mainOutput.value = TextFieldValue(text = textValue)
    }) {
        if (key.icon == null) {
            val textStyle = if (key.type == KeyType.COMMAND) {
                TextStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 22.sp
                )
            } else {
                TextStyle(
                    fontFamily = jostFontFamily,
                    fontSize = 29.sp
                )
            }
            Text(
                text = key.value,
                style = textStyle
            )
        } else {
            Icon(
                asset = key.icon,
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

val KEY_BORDER_WIDTH = 1.dp
val KEY_BORDER_COLOR = Color.Gray
val KEY_BACKGROUND = Color.Transparent
val KEY_ACTIVE_BACKGROUND = Color.White

@Composable
fun KeyView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    children: @Composable ColumnScope.() -> Unit
) {
    val active = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
            .clickable(onClick = onClick)
            .background(color = if (active.value) KEY_ACTIVE_BACKGROUND else KEY_BACKGROUND)
            .border(width = KEY_BORDER_WIDTH, color = KEY_BORDER_COLOR)
            .pointerMoveFilter(
                onEnter = {
                    active.value = true
                    false
                },
                onExit = {
                    active.value = false
                    false
                }
            ),
        children = children
    )
}

@Composable
fun EmptyKeyView(modifier: Modifier) = Box(
    modifier = modifier.fillMaxWidth()
        .background(KEY_BACKGROUND)
        .border(width = KEY_BORDER_WIDTH, color = KEY_BORDER_COLOR)
)
