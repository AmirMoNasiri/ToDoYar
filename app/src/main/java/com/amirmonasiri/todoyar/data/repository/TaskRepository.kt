package com.amirmonasiri.todoyar.data.repository

import com.amirmonasiri.todoyar.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * Contract for task data operations.
 *
 * Provides a single source of truth for task management
 * regardless of the underlying data source.
 */
interface TaskRepository {
    val tasks: Flow<List<Task>>

    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)

    suspend fun getLastInsertedTask(): Task?
}