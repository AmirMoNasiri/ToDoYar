package com.amirmonasiri.todoyar.model

import io.github.faridsolgi.persiandatetime.domain.PersianDateTime
import kotlinx.datetime.LocalTime

/**
 * Domain model representing a task in the application.
 *
 * A task may contain scheduling information,
 * completion state, reminder configuration,
 * and an optional category assignment.
 */
data class Task(
    val id: Long = 0L,
    val title: String,
    val description: String? = null,
    val categoryId: Long? = null,
    val dueDate: PersianDateTime? = null,
    val dueTime: LocalTime? = null,
    val priority: TaskPriority = TaskPriority.LOW,
    val reminder: Long? = null,
    val notifiedDue: Boolean = false,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)