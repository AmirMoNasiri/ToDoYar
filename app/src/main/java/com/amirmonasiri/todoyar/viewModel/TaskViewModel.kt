package com.amirmonasiri.todoyar.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirmonasiri.todoyar.data.repository.CategoryRepository
import com.amirmonasiri.todoyar.data.repository.TaskRepository
import com.amirmonasiri.todoyar.model.Category
import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.model.TaskPriority
import com.amirmonasiri.todoyar.utils.WeeklyActivityCalculator
import com.amirmonasiri.todoyar.view.event.TaskUiEvent
import com.amirmonasiri.todoyar.view.state.TaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing task-related UI state.
 *
 * Responsibilities:
 * - Observing tasks and categories
 * - Handling task CRUD operations
 * - Managing task form state
 * - Managing filters and selected calendar date
 * - Calculating task statistics used by the UI
 *
 * This ViewModel exposes a single [TaskUiState]
 * that is consumed by Compose screens.
 */
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState = _uiState.asStateFlow()

    /**
     * Total number of completed tasks.
     */
    val completedTasksCount
        get() = uiState.value.tasks.count {
            it.isCompleted
        }

    /**
     * Total number of pending (not completed) tasks.
     */
    val pendingTasksCount
        get() = uiState.value.tasks.count {
            !it.isCompleted
        }


    init {
        observeTasks()
        observeCategories()
        insertDefaultCategories()
    }

    /**
     * Observes task updates from the repository and
     * updates the UI state accordingly.
     *
     * Also recalculates weekly activity statistics.
     */
    private fun observeTasks() {
        viewModelScope.launch {
            repository.tasks.collect { tasks ->
                _uiState.update {
                    it.copy(
                        tasks = tasks,
                        weeklyActivity = WeeklyActivityCalculator.calculate(tasks)
                    )
                }
            }
        }
    }
    /**
     * Observes category updates from the repository.
     *
     * Automatically selects the default category when
     * no category is currently selected.
     */
    private fun observeCategories() {
        viewModelScope.launch {
            categoryRepository.categories.collect { categories ->
                _uiState.update { state ->
                    val categoryId =
                        state.categoryId
                            ?: categories
                                .firstOrNull { it.isDefault }
                                ?.id
                    state.copy(
                        categories = categories,
                        categoryId = categoryId
                    )
                }
            }
        }
    }
    /**
     * Inserts predefined categories during application startup.
     *
     * Existing categories are ignored by the repository layer.
     */
    private fun insertDefaultCategories() {
        viewModelScope.launch {
            categoryRepository.insertDefaultCategories()
        }
    }
    /**
     * Central entry point for handling UI events.
     *
     * All interactions coming from Compose screens
     * should be routed through this method.
     */
    fun onEvent(event: TaskUiEvent) {
        when (event) {
            is TaskUiEvent.TitleChanged -> {
                _uiState.update { it.copy(title = event.title) }
            }
            is TaskUiEvent.DescriptionChanged -> {
                _uiState.update { it.copy(description = event.description) }
            }
            is TaskUiEvent.DateChanged -> {
                _uiState.update { it.copy(dueDate = event.date) }
            }
            is TaskUiEvent.TimeChanged -> {
                _uiState.update { it.copy(dueTime = event.time) }
            }
            is TaskUiEvent.PriorityChanged -> {
                _uiState.update { it.copy(priority = event.priority) }
            }
            is TaskUiEvent.SaveTask -> {
                addTask()
            }
            is TaskUiEvent.UpdateTask -> {
                updateTask(event.task)
            }
            is TaskUiEvent.EditTask -> {
                _uiState.update {
                    it.copy(
                        title = event.task.title,
                        description = event.task.description.orEmpty(),
                        categoryId = event.task.categoryId,
                        dueDate = event.task.dueDate,
                        dueTime = event.task.dueTime,
                        priority = event.task.priority
                    )
                }
            }
            is TaskUiEvent.DeleteTask -> {
                deleteTask(event.task)
            }
            is TaskUiEvent.ClearForm -> {
                clearForm()
            }
            is TaskUiEvent.CompletedChanged -> {
                val updatedTask = event.task.copy(
                    isCompleted = event.isCompleted,
                    completedAt =
                        if (event.isCompleted)
                            System.currentTimeMillis()
                        else
                            null
                )

                viewModelScope.launch {
                    repository.updateTask(updatedTask)
                }
            }
            is TaskUiEvent.PriorityFilterChanged -> {
                _uiState.update {
                    it.copy(selectedPriorityFilter = event.priority)
                }
            }

            // Category
            is TaskUiEvent.AddCategory -> {
                addCategory(event.name)
            }
            is TaskUiEvent.DeleteCategory -> {
                deleteCategory(event.categoryId)
            }
            is TaskUiEvent.CategoryChanged -> {
                _uiState.update {
                    it.copy(categoryId = event.categoryId)
                }
            }
            is TaskUiEvent.CategoryFilterChanged -> {
                _uiState.update {
                    it.copy(selectedCategoryFilter = event.categoryId)
                }
            }

            is TaskUiEvent.DateSelected -> {
                _uiState.update {
                    it.copy(selectedDate = event.date)
                }
            }
        }
    }

    private fun addTask() {
        val state = uiState.value
        if (state.title.isBlank()) {
            return
        }

        val task = Task(
            title = state.title,
            description = state.description.ifBlank { null },
            categoryId = state.categoryId,
            dueDate = state.dueDate,
            dueTime = state.dueTime,
            priority = state.priority
        )

        viewModelScope.launch {
            repository.addTask(task)
            clearForm()
        }
    }

    private fun deleteTask(task: Task) {
        viewModelScope.launch { repository.deleteTask(task) }
    }

    private fun updateTask(task: Task) {
        viewModelScope.launch { repository.updateTask(task) }
    }

    private fun addCategory(name: String) {
        if (name.isBlank()) {
            return
        }

        val category = Category(
            name = name.trim(),
            isDefault = false
        )

        viewModelScope.launch { categoryRepository.addCategory(category) }
    }

    private fun deleteCategory(categoryId: Long) {

        viewModelScope.launch { categoryRepository.deleteCategory(categoryId) }
    }

    private fun clearForm() {
        val defaultCategoryId = uiState.value.categories
            .firstOrNull { it.isDefault }
            ?.id

        _uiState.update {
            it.copy(
                title = "",
                description = "",
                categoryId = defaultCategoryId,
                dueDate = null,
                dueTime = null,
                priority = TaskPriority.LOW
            )
        }
    }
}