package jetbrains.compose.calculator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetbrains.compose.calculator.resources.jostFontFamily


@Composable
fun DisplayPanel(
    modifier: Modifier,
    mainOutput: MutableState<TextFieldValue>
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = mainOutput.value.text,
            style = TextStyle(
                fontSize = 48.sp,
                fontFamily = jostFontFamily,
            ),
            overflow = TextOverflow.Ellipsis,
            softWrap = false,
            maxLines = 1,
        )
    }
}
