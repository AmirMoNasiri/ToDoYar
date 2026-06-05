package com.amirmonasiri.todoyar.view.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.duotone.AndroidLogo
import com.adamglin.phosphoricons.duotone.CalendarDots
import com.adamglin.phosphoricons.duotone.CheckSquare
import com.adamglin.phosphoricons.duotone.Gear
import com.adamglin.phosphoricons.duotone.User
import com.adamglin.phosphoricons.fill.AndroidLogo
import com.adamglin.phosphoricons.fill.CalendarDots
import com.adamglin.phosphoricons.fill.CheckSquare
import com.adamglin.phosphoricons.fill.Gear
import com.adamglin.phosphoricons.fill.User
import com.amirmonasiri.todoyar.R


sealed class Screens(
    val route: String,
    @StringRes val titleRes: Int,
    val iconDuotone: ImageVector,
    val iconFill: ImageVector
) {
    object Splash : Screens(
        "splash",
        R.string.splash,
        PhosphorIcons.Duotone.AndroidLogo,
        PhosphorIcons.Fill.AndroidLogo
    )

    object Tasks : Screens(
        "tasks",
        R.string.nav_tasks,
        PhosphorIcons.Duotone.CheckSquare,
        PhosphorIcons.Fill.CheckSquare
    )

    object Calendar : Screens(
        "calendar",
        R.string.nav_calendar,
        PhosphorIcons.Duotone.CalendarDots,
        PhosphorIcons.Fill.CalendarDots
    )

    object Profile : Screens(
        "profile",
        R.string.nav_profile,
        PhosphorIcons.Duotone.User,
        PhosphorIcons.Fill.User
    )

    object Settings : Screens(
        "settings", R.string.settings, PhosphorIcons.Duotone.Gear,
        PhosphorIcons.Fill.Gear
    )

    companion object {
        val MainScreens = listOf(Tasks, Calendar, Profile)
    }
}
