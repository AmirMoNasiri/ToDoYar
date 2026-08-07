package com.amirmonasiri.todoyar.navigation

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


// -------------------- Global --------------------
sealed class Screens(
    val route: String,
    val titleRes: Int,
    val unselectedIcon: ImageVector = PhosphorIcons.Duotone.AndroidLogo,
    val selectedIcon: ImageVector = PhosphorIcons.Fill.AndroidLogo
) {
    object Splash : Screens("splash", R.string.splash)

    // Main Screens
    object Main : Screens("main", R.string.main)


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
        val DrawerScreens = listOf(Feedback, FollowUs)
    }
}
// -------------------- Home --------------------


