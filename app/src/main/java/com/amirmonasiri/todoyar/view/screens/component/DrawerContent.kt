package com.amirmonasiri.todoyar.view.screens.component

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.duotone.BellRinging
import com.adamglin.phosphoricons.duotone.BellSlash
import com.adamglin.phosphoricons.duotone.Moon
import com.adamglin.phosphoricons.duotone.Sun
import com.amirmonasiri.todoyar.BuildConfig
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.navigation.AppScreens
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.viewModel.SettingsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Drawer content used in [MainScreen].
 *
 * Contains:
 * - App branding section
 * - Navigation items
 * - Theme switch
 * - App version information
 *
 * @param navController Navigation controller used for screen navigation.
 * @param drawerState Current drawer state.
 * @param scope Coroutine scope used for drawer open/close animations.
 * @param viewModel SettingsViewModel used for theme management.
 */
@Composable
fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val darkTheme by viewModel.isDarkTheme.collectAsState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val isNotificationEnabled by viewModel.isNotificationEnabled.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    var permissionGranted by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        )
    }

    var systemEnabled by remember {
        mutableStateOf(NotificationManagerCompat.from(context).areNotificationsEnabled())
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                permissionGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                } else true
                systemEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    var isSystemPermissionGranted by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        )
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        permissionGranted = granted
        if (granted) {
            viewModel.setNotificationEnabled(true)
            systemEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()
        }
    }

    fun openNotificationSettings() {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            }
        } else {
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
        }
        context.startActivity(intent)
    }

    fun handleNotificationToggle(isFromSwitch: Boolean = false) {
        val isSystemEnabledNow = NotificationManagerCompat.from(context).areNotificationsEnabled()
        val isPermissionGrantedNow = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else true

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !isPermissionGrantedNow -> {
                val rationale = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as androidx.activity.ComponentActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                )
                if (rationale) {
                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    openNotificationSettings()
                }
            }

            !isSystemEnabledNow -> {
                openNotificationSettings()
            }

            else -> {
                if (isFromSwitch) {
                    viewModel.toggleNotification()
                }
            }
        }
    }

    ModalDrawerSheet(modifier = Modifier.width(screenWidth * 0.65f)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // -------------------------
            // App Header
            // -------------------------
            Image(
                painter = painterResource(R.drawable.ic_todoyar_noback),
                contentDescription = "App Name",
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name_fa),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        Text(
            text = stringResource(R.string.home),
            modifier = Modifier.padding(start = 24.dp, top = 8.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        AppScreens.DrawerScreens.forEachIndexed { index, screen ->
            val isSelected = currentRoute == screen.route
            NavigationDrawerItem(
                label = { Text(screen.titleRes) },
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
                        contentDescription = screen.titleRes,
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

        val isActuallyEnabled = isNotificationEnabled && systemEnabled && permissionGranted
        NavigationDrawerItem(
            selected = false,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            onClick = {
                handleNotificationToggle(isFromSwitch = false)
            },
            label = {
                Text("اعلان")
            },
            icon = {
                Icon(
                    imageVector = if (isActuallyEnabled)
                        PhosphorIcons.Duotone.BellRinging
                    else
                        PhosphorIcons.Duotone.BellSlash,
                    contentDescription = "Notification Toggle Icon",
                    modifier = Modifier.size(Dimens.IconDefault),
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            badge = {
                Switch(
                    checked = isActuallyEnabled,
                    onCheckedChange = {
                        handleNotificationToggle(isFromSwitch = true)

                    },
                    enabled = systemEnabled && permissionGranted,
                    modifier = Modifier.size(48.dp)
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "نسخه ${BuildConfig.VERSION_NAME}",
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
