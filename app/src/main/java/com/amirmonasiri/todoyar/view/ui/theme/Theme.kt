package com.amirmonasiri.todoyar.view.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Turquoise,
    onPrimary = IceWhite,
    primaryContainer = TurquoiseDark,
    onPrimaryContainer = Color.White,

    secondary = PersianBlue,
    onSecondary = IceWhite,
    secondaryContainer = PersianBlueDark,
    onSecondaryContainer = Color.White,

    background = DarkBlueGray,
    onBackground = Color.White,
    surface = DarkSurface,
    onSurface = IceWhite,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = IceWhite.copy(alpha = 0.7f), // for hint texts

    error = ErrorColor,
    errorContainer = ErrorContainerDark,
    onError = IceWhite,

    outline = OutlineDark
)

private val LightColorScheme = lightColorScheme(
    primary = PersianBlue,
    onPrimary = IceWhite,
    primaryContainer = PersianBlueLight,
    onPrimaryContainer = PersianBlue,

    secondary = Turquoise,
    onSecondary = IceWhite,
    secondaryContainer = TurquoiseLight,
    onSecondaryContainer = Color.Black,

    background = IceWhite,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = IceWhite,
    onSurfaceVariant = Color.Black,

    error = ErrorColor,
    errorContainer = ErrorContainerLight,
    onError = IceWhite,

    outline = OutlineLight,
)

@Composable
fun ToDoYarTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}