package com.amirmonasiri.todoyar.view.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Task
import androidx.compose.ui.graphics.vector.ImageVector
import com.amirmonasiri.todoyar.R

sealed class Screens(
    var route: String,
    @StringRes val titleRes: Int,
    val icon: ImageVector
) {
    object Splash: Screens("splash", R.string.splash, Icons.Rounded.Autorenew)
    object Tasks : Screens("tasks", R.string.nav_tasks, Icons.Rounded.Task)
    object Calendar : Screens("calendar", R.string.nav_calendar, Icons.Rounded.CalendarMonth)
    object Profile : Screens("profile", R.string.nav_profile, Icons.Rounded.Person)

    companion object {
        val MainScreens = listOf(Tasks, Calendar, Profile)
    }
}