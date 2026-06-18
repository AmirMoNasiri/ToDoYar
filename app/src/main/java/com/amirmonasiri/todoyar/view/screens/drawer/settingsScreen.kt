package com.amirmonasiri.todoyar.view.screens.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.duotone.ArrowLeft
import com.adamglin.phosphoricons.duotone.ArrowRight
import com.amirmonasiri.todoyar.BuildConfig
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.utils.AppLanguage
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.viewModel.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val darkTheme by viewModel.isDarkTheme.collectAsState()
    val language by viewModel.language.collectAsState()

    var notificationsEnabled by rememberSaveable {
        mutableStateOf(true)
    }

    var showDeleteDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var showLanguageDialog by remember { mutableStateOf(false) }
    var tempSelectedLanguage by remember { mutableStateOf(language) }

    val layoutDirection = LocalLayoutDirection.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {

                Icon(
                    imageVector = if (layoutDirection == LayoutDirection.Rtl) {
                        PhosphorIcons.Duotone.ArrowRight
                    } else {
                        PhosphorIcons.Duotone.ArrowLeft
                    },
                    contentDescription = "back to main screens",
                    modifier = Modifier
                        .size(Dimens.IconDefault),
                    tint = MaterialTheme.colorScheme.primary
                )
            }


            Text(
                text = stringResource(R.string.settings),
                style = MaterialTheme.typography.headlineLarge
            )
        }

        HorizontalDivider()

        // language
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    tempSelectedLanguage = language
                    showLanguageDialog = true
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(stringResource(R.string.language))
                Text(
                    text = stringResource(R.string.change_language),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = if (language == AppLanguage.PERSIAN) stringResource(R.string.persian)
                else stringResource(R.string.english),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )


        }
        HorizontalDivider()
        // theme
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(stringResource(R.string.dark_theme))
                Text(
                    stringResource(R.string.enable_dark_theme),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Switch(
                checked = darkTheme,
                onCheckedChange = viewModel::setDarkTheme
            )
        }
        HorizontalDivider()

        // Notifications
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(stringResource(R.string.notifications))
                Text(
                    text = stringResource(R.string.enable_notifications),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Switch(
                checked = notificationsEnabled,
                onCheckedChange = {
                    notificationsEnabled = it
                }
            )
        }


        HorizontalDivider()

        OutlinedButton(
            onClick = {
                showDeleteDialog = true
            }
        ) {
            Text(stringResource(R.string.delete_all_tasks))
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.app_version))
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = BuildConfig.VERSION_NAME)
        }


    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            title = {
                Text(stringResource(R.string.delete_all_tasks_question))
            },
            text = {
                Text(stringResource(R.string.cannot_undone))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false

                        // TODO: حذف همه تسک‌ها
                    }
                ) {
                    Text(stringResource(R.string.delete))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = {
                Text(stringResource(R.string.choose_language))
            },
            text = {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { tempSelectedLanguage = AppLanguage.PERSIAN }
                            .padding(vertical = 8.dp)
                    ) {
                        RadioButton(
                            selected = tempSelectedLanguage == AppLanguage.PERSIAN,
                            onClick = { tempSelectedLanguage = AppLanguage.PERSIAN }
                        )
                        Text(
                            text = stringResource(R.string.persian),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { tempSelectedLanguage = AppLanguage.ENGLISH }
                            .padding(vertical = 8.dp)
                    ) {
                        RadioButton(
                            selected = tempSelectedLanguage == AppLanguage.ENGLISH,
                            onClick = { tempSelectedLanguage = AppLanguage.ENGLISH }
                        )
                        Text(
                            text = stringResource(R.string.english),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.setLanguage(tempSelectedLanguage)
                        showLanguageDialog = false
                    }
                ) {
                    Text(stringResource(R.string.apply))
                }
            },
            dismissButton = {
                TextButton(onClick = { showLanguageDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}
