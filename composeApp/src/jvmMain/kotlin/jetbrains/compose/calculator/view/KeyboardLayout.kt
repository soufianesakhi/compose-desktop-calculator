package jetbrains.compose.calculator.view

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import jetbrains.compose.calculator.resources.Assets
import jetbrains.compose.calculator.service.calculate
import org.jetbrains.compose.resources.DrawableResource

data class Key(
    val value: String,
    val type: KeyType,
    val icon: DrawableResource? = null,
    val onClick: ((mainOutput: MutableState<TextFieldValue>) -> Unit)? = null,
    var modifier: Modifier = Modifier
) {
    fun setModifier(modifier: Modifier): Key {
        this.modifier = modifier
        return this
    }
}

enum class KeyType {
    OPERAND, COMMAND
}

fun String.operand() = Key(this, type = KeyType.OPERAND)
fun String.command() = Key(this, type = KeyType.COMMAND)

val keyEquals = Key("=", type = KeyType.COMMAND, onClick = { mainOutput ->
    val input = mainOutput.value.text
    calculate(input)?.let { result ->
        mainOutput.value = TextFieldValue(text = result)
    }
})

val keyDelete = Key("", type = KeyType.COMMAND, icon = Assets.OutlineBackspace, onClick = { mainOutput ->
    val textValue = mainOutput.value.text
    if (textValue.isNotEmpty()) {
        mainOutput.value = TextFieldValue(
            text = if (textValue.length == 1) "0" else textValue.substring(0, textValue.length - 1)
        )
    }
})

val keyClear = Key("Clear", type = KeyType.COMMAND, onClick = { mainOutput ->
    mainOutput.value = TextFieldValue("0")
}).setModifier(Modifier.scale(0.5f))

val pointKey = ".".operand().setModifier(Modifier.offset(y = (-15).dp))

val KeyboardLayout = listOf( // columns
    listOf(
        "7".operand(),
        "4".operand(),
        "1".operand(),
        keyClear
    ),
    listOf(
        "8".operand(),
        "5".operand(),
        "2".operand(),
        "0".operand()
    ),
    listOf(
        "9".operand(),
        "6".operand(),
        "3".operand(),
        pointKey
    ),
    listOf(
        "÷".command(),
        "x".command(),
        "-".command(),
        "+".command()
    ),
    listOf(
        keyDelete,
        keyEquals
    )
)
