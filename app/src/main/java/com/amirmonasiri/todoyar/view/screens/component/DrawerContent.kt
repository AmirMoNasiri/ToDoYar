package com.amirmonasiri.todoyar.view.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.bold.Check
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
    ModalDrawerSheet{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = PhosphorIcons.Bold.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(14.dp)
                        .size(26.dp)
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Organize your daily tasks",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }


        Text(
            text = stringResource(R.string.home),
            modifier = Modifier.padding(start = 24.dp, top = 8.dp),
            style = MaterialTheme.typography.labelMedium,
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

        Text(
            text = stringResource(R.string.settings),
            modifier = Modifier.padding(start = 24.dp, top = 8.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )

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
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "نسخه 1.0.0",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(20.dp),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )
        }

        Spacer(Modifier.height(8.dp))
    }
}