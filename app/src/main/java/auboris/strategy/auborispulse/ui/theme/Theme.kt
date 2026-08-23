package auboris.strategy.auborispulse.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = AuborisAccent,
    secondary = AuborisGradientEnd,
    tertiary = AuborisSuccess,
    background = AuborisOnSurface,
    surface = AuborisPrimary,
    onPrimary = AuborisOnSurface,
    onSecondary = AuborisOnPrimary,
    onBackground = AuborisBackground,
    onSurface = AuborisOnPrimary,
    onSurfaceVariant = AuborisBorder,
    outline = AuborisMuted,
)

private val LightColorScheme = lightColorScheme(
    primary = AuborisPrimary,
    secondary = AuborisGradientEnd,
    tertiary = AuborisAccent,
    background = AuborisBackground,
    surface = AuborisSurface,
    onPrimary = AuborisOnPrimary,
    onSecondary = AuborisOnPrimary,
    onTertiary = AuborisOnSurface,
    onBackground = AuborisOnSurface,
    onSurface = AuborisOnSurface,
    onSurfaceVariant = AuborisMuted,
    outline = AuborisBorder,
)

@Composable
fun ServiceSkeletonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
