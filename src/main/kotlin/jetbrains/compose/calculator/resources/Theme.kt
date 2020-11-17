package jetbrains.compose.calculator.resources

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.platform.font

private val lightBackgroundColor = Color(0xFFf3f3f3)
private val primaryColor = Color(0xFF2376e6)

val lightThemeColors = lightColors(
    primary = primaryColor,
    background = lightBackgroundColor,
    surface = lightBackgroundColor
)

val jostFontFamily = fontFamily(
    listOf(font("jost_regular", "jost_regular.ttf"))
)
