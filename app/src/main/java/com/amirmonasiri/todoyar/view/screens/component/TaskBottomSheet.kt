package com.amirmonasiri.todoyar.view.screens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.duotone.Calendar
import com.adamglin.phosphoricons.duotone.Clock
import com.adamglin.phosphoricons.duotone.PencilSimple
import com.adamglin.phosphoricons.duotone.Trash
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.model.TaskPriority
import com.amirmonasiri.todoyar.view.event.TaskUiEvent
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.view.ui.theme.VazirFontFamily
import com.amirmonasiri.todoyar.viewModel.TaskViewModel
import io.github.faridsolgi.date_picker.view.PersianDatePicker
import io.github.faridsolgi.date_picker.view.rememberPersianDatePickerState
import io.github.faridsolgi.share.PersianDatePickerDialog
import kotlinx.datetime.LocalTime

/**
 * Bottom sheet used for creating and editing tasks.
 *
 * Features:
 * - Create new task
 * - Edit existing task
 * - Delete task
 * - Select category
 * - Select priority
 * - Select due date and time
 * - Create custom categories
 *
 * When [task] is null, the sheet works in create mode.
 * Otherwise it loads task data and works in edit mode.
 *
 * @param sheetState Modal bottom sheet state.
 * @param task Existing task for edit mode.
 * @param onDismiss Callback invoked when sheet is dismissed.
 * @param viewModel TaskViewModel instance.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskBottomSheet(
    sheetState: SheetState,
    task: Task? = null,

    onDismiss: () -> Unit,
    viewModel: TaskViewModel = hiltViewModel()
) {
    // Populate form fields when editing a task,
// otherwise reset the form for creating a new task.
    LaunchedEffect(task) {
        if (task != null) {

            viewModel.onEvent(
                TaskUiEvent.EditTask(task)
            )
        } else {

            viewModel.onEvent(
                TaskUiEvent.ClearForm
            )
        }
    }
    var showAddCategoryDialog by remember { mutableStateOf(false) }
    var newCategoryName by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    val persianDatePickerState = rememberPersianDatePickerState()
    val timePickerState = rememberTimePickerState(
        initialHour = task?.dueTime?.hour ?: 12,
        initialMinute = task?.dueTime?.minute ?: 0,
        is24Hour = true
    )


    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(
            topStart = Dimens.CornerBottomSheet,
            topEnd = Dimens.CornerBottomSheet
        ), dragHandle = {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .width(45.dp)
                    .height(5.dp)
                    .background(
                        MaterialTheme.colorScheme.outline,
                        RoundedCornerShape(50)
                    )
            )
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = if (task == null) {
                        stringResource(R.string.add_task)
                    } else {
                        stringResource(R.string.edit_task)
                    },
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(Modifier.height(Dimens.Space16))
            OutlinedTextField(
                value = uiState.title,
                onValueChange = {
                    viewModel.onEvent(
                        TaskUiEvent.TitleChanged(it)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text(stringResource(R.string.task_title)) },
                leadingIcon = { Icon(PhosphorIcons.Duotone.PencilSimple, null) },
                shape = RoundedCornerShape(Dimens.CornerBottomSheet)
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = uiState.description,
                onValueChange = {
                    viewModel.onEvent(
                        TaskUiEvent.DescriptionChanged(it)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                placeholder = { Text(stringResource(R.string.task_description)) },
                shape = RoundedCornerShape(Dimens.CornerBottomSheet)
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = stringResource(R.string.category),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(6.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                items(
                    items = uiState.categories,
                    key = { it.id }
                ) { category ->

                    FilterChip(
                        selected = uiState.categoryId == category.id,
                        onClick = {
                            viewModel.onEvent(
                                TaskUiEvent.CategoryChanged(categoryId = category.id)
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        label = { Text(text = category.name) }
                    )


                }

                // Add Category
                item {
                    FilterChip(
                        selected = false,
                        onClick = { showAddCategoryDialog = true },
                        label = { Text("+") }
                    )
                }
            }
            Spacer(Modifier.height(14.dp))

            Text(
                stringResource(R.string.task_priority),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(6.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                FilterChip(
                    selected = uiState.priority == TaskPriority.LOW, onClick = {
                        viewModel.onEvent(
                            TaskUiEvent.PriorityChanged(TaskPriority.LOW)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onBackground
                    ),
                    label = { Text(stringResource(R.string.priority_low)) })

                FilterChip(
                    selected = uiState.priority == TaskPriority.MEDIUM, onClick = {
                        viewModel.onEvent(
                            TaskUiEvent.PriorityChanged(TaskPriority.MEDIUM)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onBackground
                    ),
                    label = { Text(stringResource(R.string.priority_medium)) })
                FilterChip(
                    selected = uiState.priority == TaskPriority.HIGH, onClick = {
                        viewModel.onEvent(
                            TaskUiEvent.PriorityChanged(TaskPriority.HIGH)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onBackground
                    ),
                    label = { Text(stringResource(R.string.priority_high)) })

                FilterChip(
                    selected = uiState.priority == TaskPriority.URGENT, onClick = {
                        viewModel.onEvent(
                            TaskUiEvent.PriorityChanged(TaskPriority.URGENT)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onBackground
                    ),
                    label = { Text(stringResource(R.string.priority_urgent)) })

            }
            Spacer(Modifier.height(14.dp))

            // Date Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        PhosphorIcons.Duotone.Calendar,
                        null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Text(text = uiState.dueDate?.let { date ->
                    "${date.year}/${date.month}/${date.day}"
                } ?: "")


                IconButton(onClick = { showTimePicker = true }) {
                    Icon(
                        PhosphorIcons.Duotone.Clock,
                        null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Text(
                    text = uiState.dueTime?.let { time ->
                        String.format(
                            "%02d:%02d",
                            time.hour,
                            time.minute
                        )
                    } ?: ""
                )

            }
            Spacer(Modifier.height(16.dp))
            if (task == null) {
                Button(
                    onClick = {
                        viewModel.onEvent(TaskUiEvent.SaveTask)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(Dimens.CornerBottomSheet)
                ) {
                    Text(stringResource(R.string.save))
                }

            } else {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    IconButton(
                        onClick = {
                            viewModel.onEvent(
                                TaskUiEvent.DeleteTask(task)
                            )
                            onDismiss()
                        },
                        modifier = Modifier
                            .height(52.dp)
                            .width(52.dp)
                            .background(
                                color = MaterialTheme.colorScheme.errorContainer,
                                shape = RoundedCornerShape(
                                    Dimens.CornerBottomSheet
                                )
                            )
                    ) {

                        Icon(
                            imageVector = PhosphorIcons.Duotone.Trash,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }

                    Button(
                        onClick = {

                            val updatedTask = task.copy(
                                title = uiState.title,
                                description = uiState.description.ifBlank {
                                    null
                                },
                                categoryId = uiState.categoryId,
                                dueDate = uiState.dueDate,
                                dueTime = uiState.dueTime,
                                priority = uiState.priority
                            )

                            viewModel.onEvent(
                                TaskUiEvent.UpdateTask(updatedTask)
                            )

                            onDismiss()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        shape = RoundedCornerShape(
                            Dimens.CornerBottomSheet
                        )
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }

    // Date Picker
    if (showDatePicker) {
        PersianDatePickerDialog(onDismissRequest = {
            showDatePicker = false
        }, confirmButton = {
            TextButton(
                onClick = {
                    persianDatePickerState.selectedDate?.let { date ->
                        viewModel.onEvent(
                            TaskUiEvent.DateChanged(date)
                        )
                    }
                    showDatePicker = false
                }) { Text(stringResource(R.string.accept)) }
        }, dismissButton = {
            TextButton(onClick = { showDatePicker = false }) {
                Text(stringResource(R.string.cancel))
            }
        }

        ) {
            PersianDatePicker(
                state = persianDatePickerState, title = null

            )
        }
    }

    // Time Picker
    if (showTimePicker) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl,
            LocalTextStyle provides MaterialTheme.typography.displayLarge.copy(
                fontFamily = VazirFontFamily
            )

        ) {
            TimePickerDialog(onDismissRequest = { showTimePicker = false }, confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(
                            TaskUiEvent.TimeChanged(
                                LocalTime(
                                    hour = timePickerState.hour,
                                    minute = timePickerState.minute
                                )
                            )
                        )
                        showTimePicker = false
                    }) {
                    Text(stringResource(R.string.accept))
                }
            }, title = {}, dismissButton = {
                TextButton(
                    onClick = { showTimePicker = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }) {

                TimePicker(
                    state = timePickerState, colors = TimePickerDefaults.colors(
                        clockDialColor = MaterialTheme.colorScheme.background,
                        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.background,
                        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.background
                    )
                )


            }
        }

    }

    if (showAddCategoryDialog) {
        AlertDialog(
            onDismissRequest = {
                showAddCategoryDialog = false
                newCategoryName = ""
            },
            title = { Text("افزودن دسته‌بندی") },
            text = {
                OutlinedTextField(
                    value = newCategoryName,
                    onValueChange = { newCategoryName = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text("نام دسته‌بندی")
                    },
                    shape = RoundedCornerShape(Dimens.CornerBottomSheet)
                )
            },

            confirmButton = {
                TextButton(
                    enabled = newCategoryName.isNotBlank(),
                    onClick = {

                        viewModel.onEvent(
                            TaskUiEvent.AddCategory(
                                name = newCategoryName.trim()
                            )
                        )

                        newCategoryName = ""
                        showAddCategoryDialog = false
                    }
                ) { Text("افزودن") }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showAddCategoryDialog = false
                        newCategoryName = ""
                    }
                ) { Text("لغو") }
            }
        )
    }
}