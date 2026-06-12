package com.amirmonasiri.todoyar.view.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirmonasiri.todoyar.view.screens.drawer.feedbackScreen
import com.amirmonasiri.todoyar.view.screens.drawer.followUsScreen
import com.amirmonasiri.todoyar.view.screens.drawer.settingsScreen
import com.amirmonasiri.todoyar.view.screens.main.calendarScreen
import com.amirmonasiri.todoyar.view.screens.main.profileScreen
import com.amirmonasiri.todoyar.view.screens.main.tasksScreen

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
        composable(
            route = Screens.Feedback.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            }
        ) {
            feedbackScreen(navController)
        }

        composable(
            route = Screens.FollowUs.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            }
        ) {
            followUsScreen(navController)
        }

        composable(
            route = Screens.Settings.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            }
        ) {
            settingsScreen(navController)
        }

        // TODO: other screens
    }
}
