package com.amirmonasiri.todoyar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirmonasiri.todoyar.view.screens.main.CalendarScreen
import com.amirmonasiri.todoyar.view.screens.main.ProfileScreen
import com.amirmonasiri.todoyar.view.screens.main.TasksScreen

@Composable
fun HomeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    NavHost(
        navController,
        startDestination = HomeScreens.Calendar.route
    ) {
        composable(HomeScreens.Tasks.route) { TasksScreen(navController,contentPadding) }
        composable(HomeScreens.Calendar.route) { CalendarScreen(navController,contentPadding) }
        composable(HomeScreens.Profile.route) { ProfileScreen(navController,contentPadding) }
    }
}
