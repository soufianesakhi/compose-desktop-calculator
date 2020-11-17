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

val key0 = Key("0", type = KeyType.OPERAND)
val key1 = Key("1", type = KeyType.OPERAND)
val key2 = Key("2", type = KeyType.OPERAND)
val key3 = Key("3", type = KeyType.OPERAND)
val key4 = Key("4", type = KeyType.OPERAND)
val key5 = Key("5", type = KeyType.OPERAND)
val key6 = Key("6", type = KeyType.OPERAND)
val key7 = Key("7", type = KeyType.OPERAND)
val key8 = Key("8", type = KeyType.OPERAND)
val key9 = Key("9", type = KeyType.OPERAND)
val keyPoint = Key(".", type = KeyType.OPERAND)

val keyAddition = Key("+", type = KeyType.COMMAND)
val keySubtraction = Key("-", type = KeyType.COMMAND)
val keyMultiply = Key("x", type = KeyType.COMMAND)
val keyDivision = Key("÷", type = KeyType.COMMAND)

val keyEquals = Key("=", type = KeyType.OPERAND, onClick = { mainOutput ->
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

val KeyboardLayout = listOf(
    listOf(key7, key4, key1, key0),
    listOf(key8, key5, key2, keyPoint),
    listOf(key9, key6, key3, keyEquals),
    listOf(keyDelete, keyDivision, keyMultiply, keySubtraction, keyAddition)
)
