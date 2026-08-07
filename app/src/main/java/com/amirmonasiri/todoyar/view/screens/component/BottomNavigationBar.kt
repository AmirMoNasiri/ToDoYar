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

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .shadow(
                elevation = 24.dp,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                clip = false
            ),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        HomeScreens.MainScreens.forEach { screen ->
            val isSelected = currentRoute == screen.route

            val iconSize by animateDpAsState(
                targetValue = if (isSelected) 32.dp else 20.dp,
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
                        imageVector = if (isSelected) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = stringResource(screen.titleRes),
                        modifier = Modifier.size(iconSize)
                    )
                },
                label = {
                    if (isSelected) {
                        Text(
                            text = stringResource(screen.titleRes),
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