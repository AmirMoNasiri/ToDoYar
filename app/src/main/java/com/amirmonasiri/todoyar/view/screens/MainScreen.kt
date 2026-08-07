package com.amirmonasiri.todoyar.view.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.duotone.List
import com.adamglin.phosphoricons.duotone.PencilSimple
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.navigation.HomeNavHost
import com.amirmonasiri.todoyar.navigation.HomeScreens
import com.amirmonasiri.todoyar.view.screens.component.AddTaskBottomSheet
import com.amirmonasiri.todoyar.view.screens.component.BottomNavigationBar
import com.amirmonasiri.todoyar.view.screens.component.DrawerContent
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val homeNavController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Home Navigation
    val homeBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute = homeBackStackEntry?.destination?.route

    val showBars = currentRoute in HomeScreens.MainScreens.map { it.route }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var showBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }

    val fabPosition =
        if (LocalLayoutDirection.current == LayoutDirection.Ltr)
            FabPosition.End
        else
            FabPosition.Start

    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerContent(
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (showBars) {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = PhosphorIcons.Duotone.List,
                                    contentDescription = stringResource(R.string.menu),
                                    modifier = Modifier.size(Dimens.IconDefault),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            },

            bottomBar = {
                if (showBars) {
                    BottomNavigationBar(
                        navController = homeNavController
                    )
                }
            },
            floatingActionButtonPosition = fabPosition,
            floatingActionButton = {
                AnimatedVisibility(showBars) {
                    FloatingActionButton(
                        onClick = {
                            showBottomSheet = true
                        },
                        shape = CircleShape,
                        modifier = Modifier.size(64.dp),
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Icon(
                            imageVector = PhosphorIcons.Duotone.PencilSimple,
                            contentDescription = stringResource(R.string.add_task),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

        ) { innerPadding ->

            val contentPadding =
                if (showBars)
                    innerPadding
                else
                    WindowInsets.systemBars.asPaddingValues()

            HomeNavHost(
                navController = homeNavController,
                contentPadding = contentPadding
            )


            if (showBottomSheet) {
                AddTaskBottomSheet(
                    sheetState = sheetState,
                    onDismiss = {
                        showBottomSheet = false
                    }
                )
            }
        }
    }
}