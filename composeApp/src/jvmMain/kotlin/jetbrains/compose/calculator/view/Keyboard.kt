package jetbrains.compose.calculator.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import jetbrains.compose.calculator.resources.ICON_SIZE
import jetbrains.compose.calculator.resources.TEXT_FONT_SIZE
import org.jetbrains.compose.resources.painterResource

@Composable
fun Keyboard(
    modifier: Modifier,
    mainOutput: MutableState<TextFieldValue>
) {
    Box(modifier.background(MaterialTheme.colors.background)) {
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
    KeyView(modifier = modifier.padding(1.dp).fillMaxSize(), onClick = key.onClick?.let {
        { it(mainOutput) }
    } ?: {
        val textValue = mainOutput.value.text.let {
            if (it == "0") key.value else it + key.value
        }
        mainOutput.value = TextFieldValue(text = textValue)
    }) {
        if (key.icon == null) {
            val textStyle = if (key.type == KeyType.COMMAND) {
                TextStyle(color = MaterialTheme.colors.primary)
            } else {
                TextStyle.Default
            }
            BasicText(
                text = key.value,
                style = textStyle,
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    maxFontSize = TEXT_FONT_SIZE
                ),
                modifier = key.modifier
            )
        } else {
            Icon(
                painterResource(key.icon),
                "",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.width(ICON_SIZE).then(key.modifier)
            )
        }
    }
}

val KEY_BORDER_WIDTH = 1.dp
val KEY_BORDER_COLOR = Color.Gray

@Composable
fun KeyView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        elevation = 4.dp,
        modifier = modifier.padding(1.5.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable(onClick = onClick),
            content = content
        )
    }

}

@Composable
fun EmptyKeyView(modifier: Modifier) = Box(
    modifier = modifier.fillMaxWidth()
        .background(MaterialTheme.colors.background)
        .border(width = KEY_BORDER_WIDTH, color = KEY_BORDER_COLOR)
)
