package com.amirmonasiri.todoyar.view.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amirmonasiri.todoyar.R

// Set of Material typography styles to start with
val VazirFontFamily = FontFamily(
    Font(R.font.vazir_light_fd, weight = FontWeight.Light),
    Font(R.font.vazir_regular_fd, weight = FontWeight.Normal),
    Font(R.font.vazir_medium_fd, weight = FontWeight.Medium),
    Font(R.font.vazir_bold_fd, weight = FontWeight.Bold)
)

val SatoshiFontFamily = FontFamily(
    Font(R.font.satoshi_light, weight = FontWeight.Light),
    Font(R.font.satoshi_regular, weight = FontWeight.Normal),
    Font(R.font.satoshi_medium, weight = FontWeight.Medium),
    Font(R.font.satoshi_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = VazirFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)