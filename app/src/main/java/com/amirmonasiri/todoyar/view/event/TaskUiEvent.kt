package com.amirmonasiri.todoyar.view.event

import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.model.TaskPriority
import io.github.faridsolgi.persiandatetime.domain.PersianDateTime
import kotlinx.datetime.LocalTime

/**
 * Represents all user actions that can be performed on the task screen.
 *
 * The ViewModel receives these events through [TaskViewModel.onEvent]
 * and updates the UI state or performs the corresponding business logic.
 */
sealed interface TaskUiEvent {
    data class TitleChanged(val title: String) : TaskUiEvent
    data class DescriptionChanged(val description: String) : TaskUiEvent
    data class DateChanged(val date: PersianDateTime?) : TaskUiEvent
    data class TimeChanged(val time: LocalTime?) : TaskUiEvent
    data class PriorityChanged(val priority: TaskPriority) : TaskUiEvent
    data class CategoryChanged(val categoryId: Long?) : TaskUiEvent
    data class DeleteCategory(val categoryId: Long) : TaskUiEvent

    class AddCategory(val name: String) : TaskUiEvent
    data object SaveTask : TaskUiEvent

    data object ClearForm : TaskUiEvent

    data class CompletedChanged(
        val task: Task,
        val isCompleted: Boolean
    ) : TaskUiEvent

    data class DeleteTask(
        val task: Task
    ) : TaskUiEvent

    data class UpdateTask(
        val task: Task
    ) : TaskUiEvent

    data class EditTask(
        val task: Task
    ) : TaskUiEvent

    data class CategoryFilterChanged(
        val categoryId: Long?
    ) : TaskUiEvent

    data class PriorityFilterChanged(
        val priority: TaskPriority?
    ) : TaskUiEvent

    data class DateSelected(
        val date: PersianDateTime
    ) : TaskUiEvent
}