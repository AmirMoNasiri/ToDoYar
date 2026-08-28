package com.amirmonasiri.todoyar.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.duotone.AndroidLogo
import com.adamglin.phosphoricons.duotone.Hammer
import com.adamglin.phosphoricons.duotone.SquaresFour
import com.adamglin.phosphoricons.duotone.Users
import com.adamglin.phosphoricons.fill.AndroidLogo
import com.adamglin.phosphoricons.fill.Hammer
import com.adamglin.phosphoricons.fill.SquaresFour
import com.adamglin.phosphoricons.fill.Users

/**
 * Defines all top-level destinations of the application.
 *
 * These routes belong to the root navigation graph and include:
 * - Splash screen
 * - Main screen container
 * - Drawer screens
 *
 * App-level navigation is handled by [AppNavHost].
 */
sealed class AppScreens(
    val route: String,
    val titleRes: String,
    val unselectedIcon: ImageVector = PhosphorIcons.Duotone.AndroidLogo,
    val selectedIcon: ImageVector = PhosphorIcons.Fill.AndroidLogo
) {
    object Splash : AppScreens("splash", "اسپلش")
    object Main : AppScreens("main", "اصلی")
    object ManageCategories : AppScreens(
        "manage_categories",
        "مدیریت دسته بندی ها",
        PhosphorIcons.Duotone.SquaresFour,
        PhosphorIcons.Fill.SquaresFour
    )

    object Feedback : AppScreens(
        "feedback",
        "بازخورد",
        PhosphorIcons.Duotone.Hammer,
        PhosphorIcons.Fill.Hammer
    )

    object FollowUs : AppScreens(
        "followUs", "ارتباط با ما",
        PhosphorIcons.Duotone.Users,
        PhosphorIcons.Fill.Users
    )

    companion object {
        /**
         * Screens accessible through the Navigation Drawer.
         */
        val DrawerScreens = listOf(ManageCategories, Feedback, FollowUs)
    }
}

