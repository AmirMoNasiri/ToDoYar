package com.amirmonasiri.todoyar.view.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amirmonasiri.todoyar.view.event.TaskUiEvent
import com.amirmonasiri.todoyar.view.screens.component.task.TaskCard
import com.amirmonasiri.todoyar.viewModel.TaskViewModel
import io.github.faridsolgi.date_picker.view.PersianDatePicker
import io.github.faridsolgi.date_picker.view.PersianDatePickerDefaults
import io.github.faridsolgi.date_picker.view.rememberPersianDatePickerState

/**
 * Calendar screen of the application.
 *
 * Displays a Persian calendar and shows all tasks
 * associated with the currently selected date.
 *
 * Users can select a day from the calendar and
 * view the related tasks below it.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navController: NavController,
    contentPadding: PaddingValues,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val state = rememberPersianDatePickerState()
    val uiState by viewModel.uiState.collectAsState()

    // Observe selected date changes from PersianDatePicker
    LaunchedEffect(state.selectedDate) {
        state.selectedDate?.let { date ->
            viewModel.onEvent(
                TaskUiEvent.DateSelected(date)
            )
        }
    }
    val tasksForSelectedDate =
        uiState.tasks.filter { task ->

            val selectedDate =
                uiState.selectedDate ?: return@filter false

            val dueDate =
                task.dueDate ?: return@filter false

            dueDate.year == selectedDate.year &&
                    dueDate.month == selectedDate.month &&
                    dueDate.day == selectedDate.day
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PersianDatePicker(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = null,
            colors = PersianDatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            items(
                items = tasksForSelectedDate,
                key = { it.id }) { task ->

                TaskCard(
                    task = task,
                    category = uiState.categories.firstOrNull {
                        it.id == task.categoryId
                    },
                    onCompletedChanged = {},
                    onClick = {}
                )
            }
        }
    }
}