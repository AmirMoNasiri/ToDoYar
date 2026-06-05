package com.amirmonasiri.todoyar.view.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirmonasiri.todoyar.view.screen.calendarScreen
import com.amirmonasiri.todoyar.view.screen.profileScreen
import com.amirmonasiri.todoyar.view.screen.tasksScreen


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
        composable(route = Screens.Tasks.route) { tasksScreen(navController) }
        composable(route = Screens.Calendar.route) { calendarScreen(navController) }
        composable(route = Screens.Profile.route) { profileScreen(navController) }
    }
}
