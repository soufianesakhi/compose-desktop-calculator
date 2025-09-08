package jetbrains.compose.calculator

import androidx.compose.runtime.Composable
import de.drick.compose.hotpreview.HotPreview

@HotPreview(
    name = "phone dark", group = "dark", widthDp = 500, heightDp = 400,
    fontScale = 1f, density = 1f
)
@HotPreview(
    name = "phone", group = "light", widthDp = 400, heightDp = 800,
    fontScale = 1.5f, darkMode = false, density = 1f
)
@Composable
fun Preview() {
    App()
}