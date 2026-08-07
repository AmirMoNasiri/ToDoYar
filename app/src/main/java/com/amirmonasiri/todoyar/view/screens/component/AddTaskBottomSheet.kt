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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.duotone.Calendar
import com.adamglin.phosphoricons.duotone.Clock
import com.adamglin.phosphoricons.duotone.PencilSimple
import com.amirmonasiri.todoyar.R
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.view.ui.theme.VazirFontFamily
import io.github.faridsolgi.date_picker.view.PersianDatePicker
import io.github.faridsolgi.date_picker.view.rememberPersianDatePickerState
import io.github.faridsolgi.share.PersianDatePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("کار") }
    var selectedPriority by remember { mutableStateOf("متوسط") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    val categories = listOf("کار", "شخصی", "خرید", "ورزش", "مطالعه")
    val priorities = listOf("کم", "متوسط", "بالا", "فوری")
    val persianDatePickerState = rememberPersianDatePickerState()
    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 0,
        is24Hour = true
    )


    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(
            topStart = Dimens.CornerBottomSheet,
            topEnd = Dimens.CornerBottomSheet
        ),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(Dimens.Space16)
                    .width(45.dp)
                    .height(5.dp)
                    .background(
                        MaterialTheme.colorScheme.outline, RoundedCornerShape(50)
                    )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(R.string.add_task),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(Modifier.height(Dimens.Space16))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text(stringResource(R.string.task_title)) },
                leadingIcon = { Icon(PhosphorIcons.Duotone.PencilSimple, null) },
                shape = RoundedCornerShape(Dimens.CornerBottomSheet)
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                placeholder = { Text(stringResource(R.string.task_description)) },
                shape = RoundedCornerShape(Dimens.CornerBottomSheet)
            )
            Spacer(Modifier.height(14.dp))
            Text(
                stringResource(R.string.category),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                categories.forEach {
                    FilterChip(
                        selected = selectedCategory == it,
                        onClick = { selectedCategory = it },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onBackground
                        ),
                        label = { Text(it) }
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
                priorities.forEach {
                    FilterChip(
                        selected = selectedPriority == it,
                        onClick = { selectedPriority = it },
                        label = { Text(it) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
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
                if (selectedDate.isNotEmpty()) {
                    Text(selectedDate)
                }


                IconButton(onClick = { showTimePicker = true }
                ) {
                    Icon(
                        PhosphorIcons.Duotone.Clock,
                        null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                if (selectedTime.isNotEmpty()) {
                    Text(selectedTime)
                }
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(Dimens.CornerBottomSheet)
            ) {
                Text(
                    stringResource(
                        R.string.save
                    )
                )
            }
            Spacer(Modifier.height(12.dp))
        }
    }
//    }

    // Date Picker
    if (showDatePicker) {
        PersianDatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        persianDatePickerState.selectedDate?.let { date ->
                            selectedDate =
                                "${date.year}/${date.month}/${date.day}"

                        }
                        showDatePicker = false
                    }
                ) { Text(stringResource(R.string.accept)) }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }

        ) {
            PersianDatePicker(
                state = persianDatePickerState,
                title = null

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
            TimePickerDialog(
                onDismissRequest = { showTimePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedTime = "%02d:%02d".format(
                                timePickerState.hour, timePickerState.minute
                            )
                            showTimePicker = false
                        }
                    ) {
                        Text(stringResource(R.string.accept))
                    }
                },
                title = {},
                dismissButton = {
                    TextButton(
                        onClick = { showTimePicker = false }
                    ) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            ) {

                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = MaterialTheme.colorScheme.background,
                        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.background,
                        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.background
                    )
                )


            }
        }

    }
}