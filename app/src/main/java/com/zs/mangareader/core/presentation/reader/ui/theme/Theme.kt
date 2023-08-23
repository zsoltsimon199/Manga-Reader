package com.zs.mangareader.core.presentation.reader.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.zs.mangareader.core.presentation.reader.ui.ThemeChooser

private val DarkColorScheme = darkColorScheme(
    primary = Primary80,
    primaryContainer = Primary30,
    onPrimary = Primary20,
    onPrimaryContainer = Primary90,
    inversePrimary = Primary40,
    secondary = Secondary80,
    secondaryContainer = Secondary30,
    onSecondary = Secondary20,
    onSecondaryContainer = Secondary90,
    tertiary = Tertiary80,
    tertiaryContainer = Tertiary30,
    onTertiary = Tertiary20,
    onTertiaryContainer = Tertiary90,
    surface = Neutral6,
    surfaceDim = Neutral6,
    surfaceBright = Neutral24,
    surfaceContainerLowest = Neutral4,
    surfaceContainerLow = Neutral10,
    surfaceContainer = Neutral12,
    surfaceContainerHigh = Neutral17,
    surfaceContainerHighest = Neutral22,
    surfaceVariant = NeutralVariant30,
    onSurface = Neutral90,
    onSurfaceVariant = NeutralVariant80,
    inverseSurface = Neutral90,
    inverseOnSurface = Neutral20,
    background = Neutral6,
    onBackground = Neutral90,
    error = Error80,
    errorContainer = Error30,
    onError = Error20,
    onErrorContainer = Error90,
    outline = NeutralVariant60,
    outlineVariant = NeutralVariant30,
    surfaceTint = Primary80,
    scrim = Neutral0
)

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    primaryContainer = Primary90,
    onPrimary = Primary100,
    onPrimaryContainer = Primary10,
    inversePrimary = Primary80,
    secondary = Secondary40,
    secondaryContainer = Secondary90,
    onSecondary = Secondary100,
    onSecondaryContainer = Secondary10,
    tertiary = Tertiary40,
    tertiaryContainer = Tertiary90,
    onTertiary = Tertiary100,
    onTertiaryContainer = Tertiary10,
    surface = Neutral98,
    surfaceDim = Neutral87,
    surfaceBright = Neutral98,
    surfaceContainerLowest = Neutral100,
    surfaceContainerLow = Neutral96,
    surfaceContainer = Neutral94,
    surfaceContainerHigh = Neutral92,
    surfaceContainerHighest = Neutral90,
    surfaceVariant = NeutralVariant90,
    onSurface = Neutral10,
    onSurfaceVariant = NeutralVariant30,
    inverseSurface = Neutral20,
    inverseOnSurface = Neutral95,
    background = Neutral98,
    onBackground = Neutral10,
    error = Error40,
    errorContainer = Error90,
    onError = Error100,
    onErrorContainer = Error10,
    outline = NeutralVariant50,
    outlineVariant = NeutralVariant50,
    surfaceTint = Primary40,
    scrim = Neutral0
)

@Composable
fun MangaReaderTheme(
    //darkTheme: Boolean = isSystemInDarkTheme(),
    darkTheme: Boolean = ThemeChooser.isDarkTheme.collectAsState().value,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = pickColorScheme(dynamicColor, darkTheme)
    val view = LocalView.current

    if (!view.isInEditMode) {
        val currentWindow = (view.context as? Activity)?.window
            ?: throw Exception("Not in an activity - unable to get Window reference")

        SideEffect {
            currentWindow.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars =
                darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/**
 * ## pickColorScheme
 * Picks which Color Scheme to use based on Android Version and Dark Mode being enabled.
 */
@Composable
fun pickColorScheme(
    dynamicColor: Boolean,
    darkTheme: Boolean
): ColorScheme {
    return when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
}