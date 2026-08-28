package com.amirmonasiri.todoyar.view.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amirmonasiri.todoyar.view.screens.component.profile.CategoryDonutChart
import com.amirmonasiri.todoyar.view.screens.component.profile.CompletionSection
import com.amirmonasiri.todoyar.view.screens.component.profile.ProfileHeader
import com.amirmonasiri.todoyar.view.screens.component.profile.ProfileWeeklyChart
import com.amirmonasiri.todoyar.view.screens.component.profile.StatsSection
import com.amirmonasiri.todoyar.viewModel.TaskViewModel

/**
 * Profile screen of the application.
 *
 * Displays an overview of the user's productivity and task statistics,
 * including:
 *
 * - Completed and pending task counts
 * - Task completion rate
 * - Weekly activity chart
 * - Task distribution by category
 *
 * All data is provided by [TaskViewModel] and updated automatically
 * through StateFlow collection.
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    contentPadding: PaddingValues,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    // Task statistics
    val completedCount = viewModel.completedTasksCount

    val pendingCount = viewModel.pendingTasksCount


    val totalTasks = uiState.tasks.size
    // Overall completion percentage
    val completionRate =
        if (totalTasks == 0) {
            0f
        } else {
            completedCount.toFloat() / totalTasks.toFloat()
        }

    // Weekly activity data used by the bar chart
    val weekData =
        uiState.weeklyActivity.ifEmpty {
            List(7) { 0 }
        }
    // Map category IDs to category names
    val categoriesById = uiState.categories.associateBy { it.id }

    // Number of pending tasks per category
    val categoryCounts =
        uiState.tasks
            .filter { !it.isCompleted }
            .groupBy { task ->
                categoriesById[task.categoryId]?.name
                    ?: "بدون دسته‌بندی"
            }
            .mapValues { (_, tasks) ->
                tasks.size
            }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { ProfileHeader() }
            item {
                StatsSection(
                    completed = completedCount,
                    pending = pendingCount
                )
            }
            item { CategoryDonutChart(categoryCounts = categoryCounts) }

            item {
                CompletionSection(completionRate)
            }
            item { ProfileWeeklyChart(weekData = weekData) }
        }
    }

}
