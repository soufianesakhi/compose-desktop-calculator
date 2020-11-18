package jetbrains.compose.calculator.resources

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.platform.font
import androidx.compose.ui.unit.dp

val CALCULATOR_PADDING = 4.dp

val lightThemeColors = lightColors(
    primary = Color(0xFF2376e6),
    background = Color(0xFFF0F0F0),
    surface = Color(0xFFF5F5F5)
)

val jostFontFamily = fontFamily(
    listOf(font("jost_regular", "jost_regular.ttf"))
)
