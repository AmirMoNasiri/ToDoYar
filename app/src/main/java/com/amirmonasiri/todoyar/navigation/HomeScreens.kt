package com.amirmonasiri.todoyar.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.duotone.AndroidLogo
import com.adamglin.phosphoricons.duotone.CalendarDots
import com.adamglin.phosphoricons.duotone.CheckSquare
import com.adamglin.phosphoricons.duotone.User
import com.adamglin.phosphoricons.fill.AndroidLogo
import com.adamglin.phosphoricons.fill.CalendarDots
import com.adamglin.phosphoricons.fill.CheckSquare
import com.adamglin.phosphoricons.fill.User
import com.amirmonasiri.todoyar.R

sealed class HomeScreens(
    val route: String,
    val titleRes: Int,
    val unselectedIcon: ImageVector = PhosphorIcons.Duotone.AndroidLogo,
    val selectedIcon: ImageVector = PhosphorIcons.Fill.AndroidLogo
) {
    object Tasks : HomeScreens(
        "tasks",
        R.string.nav_tasks,
        PhosphorIcons.Duotone.CheckSquare,
        PhosphorIcons.Fill.CheckSquare
    )

    object Calendar : HomeScreens(
        "calendar",
        R.string.nav_calendar,
        PhosphorIcons.Duotone.CalendarDots,
        PhosphorIcons.Fill.CalendarDots
    )

    // Drawer Screens
    object Profile : HomeScreens(
        "profile",
        R.string.nav_profile,
        PhosphorIcons.Duotone.User,
        PhosphorIcons.Fill.User
    )

    companion object {
        val MainScreens = listOf(Tasks, Calendar, Profile)
    }
}