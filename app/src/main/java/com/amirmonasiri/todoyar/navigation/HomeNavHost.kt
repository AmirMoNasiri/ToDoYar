package com.amirmonasiri.todoyar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirmonasiri.todoyar.view.screens.main.CalendarScreen
import com.amirmonasiri.todoyar.view.screens.main.ProfileScreen
import com.amirmonasiri.todoyar.view.screens.main.TasksScreen

/**
 * Navigation graph for the main part of the application.
 *
 * Handles navigation between:
 * - Tasks
 * - Calendar
 * - Profile
 *
 * This NavHost is hosted inside [MainScreen].
 */
@Composable
fun HomeNavHost(
    navController: NavHostController,
    contentPadding: PaddingValues
) {
    NavHost(
        navController,
        startDestination = HomeScreens.Calendar.route
    ) {
        composable(HomeScreens.Tasks.route) { TasksScreen(navController, contentPadding) }
        composable(HomeScreens.Calendar.route) { CalendarScreen(navController, contentPadding) }
        composable(HomeScreens.Profile.route) { ProfileScreen(navController, contentPadding) }
    }
}