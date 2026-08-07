package com.amirmonasiri.todoyar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirmonasiri.todoyar.view.screens.MainScreen
import com.amirmonasiri.todoyar.view.screens.SplashScreen
import com.amirmonasiri.todoyar.view.screens.drawer.FeedbackScreen
import com.amirmonasiri.todoyar.view.screens.drawer.FollowUsScreen
import com.amirmonasiri.todoyar.view.screens.main.CalendarScreen
import com.amirmonasiri.todoyar.view.screens.main.ProfileScreen
import com.amirmonasiri.todoyar.view.screens.main.TasksScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
    ) {
        composable(Screens.Splash.route) { SplashScreen(navController) }
        composable(Screens.Main.route) { MainScreen(navController) }

        // Drawer Screens
        composable(Screens.Feedback.route) { FeedbackScreen(navController) }
        composable(Screens.FollowUs.route) { FollowUsScreen(navController) }
    }
}