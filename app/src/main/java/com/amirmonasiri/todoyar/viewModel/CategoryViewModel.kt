package com.amirmonasiri.todoyar.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirmonasiri.todoyar.data.repository.CategoryRepository
import com.amirmonasiri.todoyar.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for category management.
 *
 * Responsibilities:
 * - Exposes category data to the UI.
 * - Inserts default categories on first launch.
 * - Handles add, update and delete category operations.
 * - Prevents invalid category actions such as
 *   creating empty categories or removing default categories.
 */
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    /**
     * Observable stream of all available categories.
     *
     * Converted to StateFlow to provide a lifecycle-aware
     * state holder for Compose screens.
     */
    val categories = repository.categories.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        // Ensures default application categories
        // exist when the ViewModel is created.
        viewModelScope.launch {
            repository.insertDefaultCategories()
        }
    }

    fun addCategory(name: String) {
        if (name.isBlank()) return

        viewModelScope.launch {
            repository.addCategory(
                Category(
                    name = name.trim(),
                    isDefault = false
                )
            )
        }
    }

    fun updateCategory(category: Category) {
        if (category.name.isBlank()) return

        viewModelScope.launch {
            repository.updateCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        if (category.isDefault) return
        viewModelScope.launch {
            repository.deleteCategory(category.id)
        }
    }
}