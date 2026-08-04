package com.amirmonasiri.todoyar.view.screens.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.duotone.Moon
import com.adamglin.phosphoricons.duotone.Sun
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.navigation.Screens
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.viewModel.SettingsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val darkTheme by viewModel.isDarkTheme.collectAsState()
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


        NavigationDrawerItem(
            selected = false,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            onClick = {},
            label = {
                Text(stringResource(R.string.dark_theme))
            },
            icon = {
                Icon(
                    imageVector = if (darkTheme)
                        PhosphorIcons.Duotone.Moon
                    else
                        PhosphorIcons.Duotone.Sun,

                    contentDescription = "Dark Theme Icon",
                    modifier = Modifier.size(Dimens.IconDefault),
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            badge = {
                Switch(
                    checked = darkTheme,
                    onCheckedChange = viewModel::setDarkTheme
                )
            }
        )
    }
}