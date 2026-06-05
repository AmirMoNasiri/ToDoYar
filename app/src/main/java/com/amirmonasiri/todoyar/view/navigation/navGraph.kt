package com.amirmonasiri.todoyar.view.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun setupNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Tasks.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = Screens.Tasks.route) {  }
        composable(route = Screens.Calendar.route) {  }
        composable(route = Screens.Profile.route) {  }
    }
}