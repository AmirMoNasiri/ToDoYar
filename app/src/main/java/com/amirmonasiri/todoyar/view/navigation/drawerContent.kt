package com.amirmonasiri.todoyar.view.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun drawerContent(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    ModalDrawerSheet {
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.primary,
            fontSize = Dimens.TitleSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                vertical = Dimens.Space8,
                horizontal = Dimens.Space16
            )
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                vertical = Dimens.Space8,
                horizontal = Dimens.Space12
            ),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )

        Screens.DrawerScreens.forEachIndexed { index, screen ->
            val isSelected = currentRoute == screen.route
            NavigationDrawerItem(
                label = { Text(stringResource(screen.titleRes)) },
                selected = isSelected,
                onClick = {
                    scope.launch { drawerState.close() }
                    if (!isSelected) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = stringResource(screen.titleRes),
                        modifier = Modifier.size(Dimens.IconDefault),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}