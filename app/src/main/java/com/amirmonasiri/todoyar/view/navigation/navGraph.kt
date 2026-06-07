package com.amirmonasiri.todoyar.view.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirmonasiri.todoyar.view.screens.calendarScreen
import com.amirmonasiri.todoyar.view.screens.feedbackScreen
import com.amirmonasiri.todoyar.view.screens.followUsScreen
import com.amirmonasiri.todoyar.view.screens.profileScreen
import com.amirmonasiri.todoyar.view.screens.settingsScreen
import com.amirmonasiri.todoyar.view.screens.tasksScreen

@Composable
fun setupNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Calendar.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        // Main Screens
        composable(route = Screens.Tasks.route) { tasksScreen(navController) }
        composable(route = Screens.Calendar.route) { calendarScreen(navController) }
        composable(route = Screens.Profile.route) { profileScreen(navController) }

        // Drawer Screens
        composable(route = Screens.Feedback.route) { feedbackScreen(navController) }
        composable(route = Screens.FollowUs.route) { followUsScreen(navController) }
        composable(route = Screens.Settings.route) { settingsScreen(navController) }

        // TODO: other screens
    }
}
