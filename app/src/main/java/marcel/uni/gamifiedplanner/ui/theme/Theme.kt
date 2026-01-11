package marcel.uni.gamifiedplanner.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = LightGrayDark,
    tertiary = DarkGrayDark,
    background = DarkDark,
    surface = MediumGrayDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = black,
    tertiary = MediumGrayLight,
    background = LightLight,
    surface = DarkGrayLight,

)

// this needs fixes later to get it to work with custom settings

@Composable
fun GamifiedPlannerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val colorScheme: ColorScheme = when{
        darkTheme->DarkColorScheme
        else->LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
