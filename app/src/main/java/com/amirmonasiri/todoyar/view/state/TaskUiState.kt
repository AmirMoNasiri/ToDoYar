package com.amirmonasiri.todoyar.view.state

import com.amirmonasiri.todoyar.model.Category
import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.model.TaskPriority
import io.github.faridsolgi.persiandatetime.domain.PersianDateTime
import kotlinx.datetime.LocalTime

/**
 * Represents the complete UI state of the task screen.
 *
 * This state is exposed by [TaskViewModel] and collected by Compose UI.
 * Every UI change should be reflected through a new instance of this state.
 */
data class TaskUiState(

    // ---------------- Tasks ----------------
    val tasks: List<Task> = emptyList(),
    val weeklyActivity: List<Int> = emptyList(),

    // ---------------- Categories ----------------
    val categories: List<Category> = emptyList(),

    // ---------------- Selected Category ---------------
    val categoryId: Long? = null,

    // ---------------- Task Form ----------------
    val title: String = "",
    val description: String = "",
    val dueDate: PersianDateTime? = null,
    val dueTime: LocalTime? = null,
    val priority: TaskPriority = TaskPriority.LOW,

    // ---------------- Filters ----------------
    val selectedCategoryFilter: Long? = null,
    val selectedPriorityFilter: TaskPriority? = null,

    // ---------------- Calendar ----------------
    val selectedDate: PersianDateTime? = null
)