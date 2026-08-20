package com.amirmonasiri.todoyar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirmonasiri.todoyar.view.screens.MainScreen
import com.amirmonasiri.todoyar.view.screens.SplashScreen
import com.amirmonasiri.todoyar.view.screens.drawer.FeedbackScreen
import com.amirmonasiri.todoyar.view.screens.drawer.FollowUsScreen
import com.amirmonasiri.todoyar.view.screens.drawer.ManageCategoriesScreen

/**
 * Root navigation graph of the application.
 *
 * Responsible for navigation between top-level screens such as:
 * - Splash
 * - Main container
 * - Drawer destinations
 *
 * This is the entry point of Navigation Compose.
 */
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route,
    ) {
        composable(AppScreens.Splash.route) { SplashScreen(navController) }
        composable(AppScreens.Main.route) { MainScreen(navController) }

        // Drawer Screens
        composable(AppScreens.ManageCategories.route) { ManageCategoriesScreen(navController) }
        composable(AppScreens.Feedback.route) { FeedbackScreen(navController) }
        composable(AppScreens.FollowUs.route) { FollowUsScreen(navController) }
    }
}