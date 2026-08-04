package com.amirmonasiri.todoyar.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirmonasiri.todoyar.view.screens.drawer.FeedbackScreen
import com.amirmonasiri.todoyar.view.screens.drawer.FollowUsScreen
import com.amirmonasiri.todoyar.view.screens.main.CalendarScreen
import com.amirmonasiri.todoyar.view.screens.main.ProfileScreen
import com.amirmonasiri.todoyar.view.screens.main.TasksScreen

private const val NAV_ANIMATION_DURATION = 400

@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val layoutDirection = LocalLayoutDirection.current

    NavHost(
        navController = navController,
        startDestination = Screens.Calendar.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        /*
         * Main Screens
         */

        composable(
            route = Screens.Tasks.route
        ) {
            TasksScreen(
                navController = navController
            )
        }

        composable(
            route = Screens.Calendar.route
        ) {
            CalendarScreen(
                navController = navController
            )
        }

        composable(
            route = Screens.Profile.route
        ) {
            ProfileScreen(
                navController = navController
            )
        }

        /*
         * Drawer Screens
         */
        fun AnimatedContentTransitionScope<*>.enterDirection(
            layoutDirection: LayoutDirection
        ): AnimatedContentTransitionScope.SlideDirection {
            return if (layoutDirection == LayoutDirection.Rtl) {
                AnimatedContentTransitionScope.SlideDirection.Left
            } else {
                AnimatedContentTransitionScope.SlideDirection.Right
            }
        }

        fun AnimatedContentTransitionScope<*>.exitDirection(
            layoutDirection: LayoutDirection
        ): AnimatedContentTransitionScope.SlideDirection {
            return if (layoutDirection == LayoutDirection.Rtl) {
                AnimatedContentTransitionScope.SlideDirection.Right
            } else {
                AnimatedContentTransitionScope.SlideDirection.Left
            }
        }

        composable(
            route = Screens.Feedback.route,
            enterTransition = {
                slideIntoContainer(
                    enterDirection(layoutDirection),
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    exitDirection(layoutDirection),
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                )
            }
        ) {
            FeedbackScreen(
                navController = navController
            )
        }

        composable(
            route = Screens.FollowUs.route,
            enterTransition = {
                slideIntoContainer(
                    enterDirection(layoutDirection),
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    exitDirection(layoutDirection),
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                )
            }
        ) {
            FollowUsScreen(
                navController = navController
            )
        }

    }
}