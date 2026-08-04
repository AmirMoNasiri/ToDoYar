package com.amirmonasiri.todoyar.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.duotone.AndroidLogo
import com.adamglin.phosphoricons.duotone.CalendarDots
import com.adamglin.phosphoricons.duotone.CheckSquare
import com.adamglin.phosphoricons.duotone.Hammer
import com.adamglin.phosphoricons.duotone.User
import com.adamglin.phosphoricons.duotone.Users
import com.adamglin.phosphoricons.fill.AndroidLogo
import com.adamglin.phosphoricons.fill.CalendarDots
import com.adamglin.phosphoricons.fill.CheckSquare
import com.adamglin.phosphoricons.fill.Hammer
import com.adamglin.phosphoricons.fill.User
import com.adamglin.phosphoricons.fill.Users
import com.amirmonasiri.todoyar.R

sealed class Screens(
    val route: String,
    @StringRes val titleRes: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    object Splash : Screens(
        "splash",
        R.string.splash,
        PhosphorIcons.Duotone.AndroidLogo,
        PhosphorIcons.Fill.AndroidLogo
    )

    // Main Screens
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

    // Drawer Screens
    object Feedback : Screens(
        "feedback", R.string.feedback,
        PhosphorIcons.Duotone.Hammer,
        PhosphorIcons.Fill.Hammer
    )

    object FollowUs : Screens(
        "followUs", R.string.follow_us,
        PhosphorIcons.Duotone.Users,
        PhosphorIcons.Fill.Users
    )


    companion object {
        val MainScreens = listOf(Tasks, Calendar, Profile)
        val DrawerScreens = listOf(Feedback, FollowUs)

        fun titleResFor(route: String?): Int = when (route) {
            Tasks.route -> Tasks.titleRes
            Calendar.route -> Calendar.titleRes
            Profile.route -> Profile.titleRes
            Feedback.route -> Feedback.titleRes
            FollowUs.route -> FollowUs.titleRes
            else -> Calendar.titleRes
        }
    }
}
