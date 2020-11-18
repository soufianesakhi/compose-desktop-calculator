package jetbrains.compose.calculator.view

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.text.input.TextFieldValue
import jetbrains.compose.calculator.resources.Assets
import jetbrains.compose.calculator.service.calculate

data class Key(
    val value: String,
    val type: KeyType,
    val icon: VectorAsset? = null,
    val onClick: ((mainOutput: MutableState<TextFieldValue>) -> Unit)? = null
)

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
            text = if(textValue.length == 1) "0" else textValue.substring(0, textValue.length - 1)
        )
    }
})

val keyClear = Key("Clear", type = KeyType.COMMAND, onClick = { mainOutput ->
    mainOutput.value = TextFieldValue("0")
})

val KeyboardLayout = listOf(
    listOf("7".operand(), "4".operand(), "1".operand(), keyClear),
    listOf("8".operand(), "5".operand(), "2".operand(), "0".operand()),
    listOf("9".operand(), "6".operand(), "3".operand(), ".".operand()),
    listOf("÷".command(), "x".command(), "-".command(), "+".command()),
    listOf(keyDelete, keyEquals)
)
