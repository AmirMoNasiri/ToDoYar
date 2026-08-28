package com.amirmonasiri.todoyar.view.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.view.event.TaskUiEvent
import com.amirmonasiri.todoyar.view.screens.component.TaskBottomSheet
import com.amirmonasiri.todoyar.view.screens.component.task.EmptyTasks
import com.amirmonasiri.todoyar.view.screens.component.task.TaskCard
import com.amirmonasiri.todoyar.viewModel.TaskViewModel

/**
 * Main task management screen.
 *
 * Displays:
 * - Category filters
 * - Task list
 * - Empty state when no tasks exist
 * - Task details/edit bottom sheet
 *
 * Users can filter tasks, mark them as completed,
 * and open a task for editing.
 *
 * @param navController Navigation controller.
 * @param contentPadding Padding provided by the parent Scaffold.
 * @param viewModel TaskViewModel used to manage task state.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    navController: NavController,
    contentPadding: PaddingValues,
    viewModel: TaskViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    // Currently selected task for editing
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    // Controls visibility of the task bottom sheet
    var showTaskSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    // Tasks filtered by selected category and priority
    val filteredTasks =
        uiState.tasks
            .filter { task ->

                val categoryMatch =
                    uiState.selectedCategoryFilter == null ||
                            task.categoryId == uiState.selectedCategoryFilter

                val priorityMatch =
                    uiState.selectedPriorityFilter == null ||
                            task.priority == uiState.selectedPriorityFilter

                categoryMatch && priorityMatch
            }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            // Category filter chips
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                item {
                    FilterChip(
                        selected = uiState.selectedCategoryFilter == null,
                        onClick = {
                            viewModel.onEvent(
                                TaskUiEvent.CategoryFilterChanged(null)
                            )
                        },
                        label = {
                            Text("همه")
                        }
                    )
                }

                items(uiState.categories) { category ->

                    FilterChip(
                        selected =
                            uiState.selectedCategoryFilter == category.id,

                        onClick = {
                            viewModel.onEvent(
                                TaskUiEvent.CategoryFilterChanged(
                                    category.id
                                )
                            )
                        },

                        label = {
                            Text(category.name)
                        }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Empty state when no tasks are available
            if (uiState.tasks.isEmpty()) {
                EmptyTasks()
            } else {

                // List of tasks
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        items = filteredTasks,
                        key = { it.id }
                    ) { task ->

                        TaskCard(
                            task = task,
                            category = uiState.categories.firstOrNull {
                                it.id == task.categoryId
                            },
                            onCompletedChanged = { completed ->
                                viewModel.onEvent(
                                    TaskUiEvent.CompletedChanged(
                                        task = task,
                                        isCompleted = completed
                                    )
                                )
                            },
                            onClick = {
                                selectedTask = task
                                showTaskSheet = true
                            }
                        )
                    }
                }
            }
        }
    }
    // Task details / edit bottom sheet
    if (showTaskSheet && selectedTask != null) {

        TaskBottomSheet(
            sheetState = sheetState,
            task = selectedTask,

            onDismiss = {
                showTaskSheet = false
                selectedTask = null
            }
        )
    }
}