package com.amirmonasiri.todoyar.view.screens.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.amirmonasiri.todoyar.navigation.HomeScreens
import com.amirmonasiri.todoyar.view.ui.theme.Dimens

/**
 * Bottom navigation bar for the main sections of the application.
 *
 * Displays all items defined in [HomeScreens.MainScreens] and
 * highlights the currently selected destination.
 *
 * Features:
 * - Animated icon scaling for the selected item
 * - State restoration between destinations
 * - Single top navigation behavior
 *
 * @param navController Navigation controller used for screen navigation.
 */
@Composable
fun BottomNavigationBar(navController: NavController) {

    // Current destination from Navigation back stack
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .shadow(
                elevation = 24.dp,
                shape = RoundedCornerShape(
                    topStart = Dimens.Corner,
                    topEnd = Dimens.Corner
                ),
                clip = false
            ),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        // Render all main navigation destinations
        HomeScreens.MainScreens.forEach { screen ->
            val isSelected = currentRoute == screen.route

            val iconSize by animateDpAsState(
                targetValue = if (isSelected) Dimens.IconRegular else Dimens.IconMedium,
                animationSpec = spring(
                    dampingRatio = 0.5f,
                    stiffness = 400f
                )
            )

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) screen.selectedIcon
                        else screen.unselectedIcon,
                        contentDescription = screen.titleRes,
                        modifier = Modifier.size(iconSize)
                    )
                },
                label = {
                    if (isSelected) {
                        Text(
                            text = screen.titleRes,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}