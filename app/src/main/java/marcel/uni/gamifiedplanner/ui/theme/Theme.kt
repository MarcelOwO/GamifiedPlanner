package marcel.uni.gamifiedplanner.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = LightGrayDark,
    tertiary = MediumGrayDark,
    background = DarkDark,
    surface = DarkGrayDark,
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = LightGrayLight,
    tertiary = MediumGrayLight,
    background = LightLight,
    surface = DarkGrayLight,

)

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