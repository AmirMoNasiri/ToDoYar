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

/**
 * Defines all destinations that belong to the main section of the app.
 *
 * These screens are displayed inside [HomeNavHost] and are used
 * by the Bottom Navigation Bar and Profile section.
 *
 * Each destination contains:
 * - route used by Navigation Compose
 * - title string resource
 * - selected / unselected icons
 */
sealed class HomeScreens(
    val route: String,
    val titleRes: String,
    val unselectedIcon: ImageVector = PhosphorIcons.Duotone.AndroidLogo,
    val selectedIcon: ImageVector = PhosphorIcons.Fill.AndroidLogo
) {
    object Tasks : HomeScreens(
        "tasks",
        "وظایف",
        PhosphorIcons.Duotone.CheckSquare,
        PhosphorIcons.Fill.CheckSquare
    )

    object Calendar : HomeScreens(
        "calendar",
        "تقویم",
        PhosphorIcons.Duotone.CalendarDots,
        PhosphorIcons.Fill.CalendarDots
    )

    object Profile : HomeScreens(
        "profile",
        "پروفایل",
        PhosphorIcons.Duotone.User,
        PhosphorIcons.Fill.User
    )

    companion object {
        /**
         * Screens displayed in the main navigation area.
         *
         * Used to build the Bottom Navigation Bar.
         */
        val MainScreens = listOf(Tasks, Calendar, Profile)
    }
}