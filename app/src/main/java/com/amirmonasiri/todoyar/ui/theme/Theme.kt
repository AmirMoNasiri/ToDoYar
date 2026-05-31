package com.amirmonasiri.todoyar.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Turquoise,
    onPrimary = IceWhite,
    secondary = PersianBlue,
    onSecondary = IceWhite,
    background = DarkBlueGray,
    onBackground = IceWhite,
    surface = Color(0xFF1A2234),
    onSurface = IceWhite,
    primaryContainer = Color(0xFF004D4D),
    onPrimaryContainer = IceWhite
)

private val LightColorScheme = lightColorScheme(
    primary = PersianBlue,
    onPrimary = IceWhite,
    secondary = Turquoise,
    onSecondary = IceWhite,
    background = IceWhite,
    onBackground = DarkBlueGray,
    surface = Color.White,
    onSurface = DarkBlueGray,
    primaryContainer = Color(0xFFDDE4FF),
    onPrimaryContainer = PersianBlue
)

@Composable
fun ToDoYarTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}