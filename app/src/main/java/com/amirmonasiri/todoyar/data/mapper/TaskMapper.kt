package com.amirmonasiri.todoyar.data.mapper

import com.amirmonasiri.todoyar.data.local.database.entity.TaskEntity
import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.model.TaskPriority

/**
 * Converts a Room [TaskEntity] into a domain [Task] model.
 *
 * The priority value is stored as an integer in the database
 * and restored as a [TaskPriority] enum when loaded.
 *
 * If the stored value is invalid, [TaskPriority.LOW] is used
 * as a safe fallback.
 */
fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        categoryId = categoryId,
        dueDate = dueDate,
        dueTime = dueTime,
        priority = TaskPriority.entries.getOrElse(priority) {
            TaskPriority.LOW
        },
        reminder = reminder,
        notifiedDue = notifiedDue,
        isCompleted = isCompleted,
        completedAt = completedAt,
        createdAt = createdAt
    )
}

/**
 * Converts a domain [Task] model into a Room [TaskEntity].
 *
 * The task priority is converted from a [TaskPriority] enum
 * into its ordinal integer value before being stored.
 */
fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        categoryId = categoryId,
        priority = priority.ordinal,
        dueDate = dueDate,
        dueTime = dueTime,
        reminder = reminder,
        notifiedDue = notifiedDue,
        isCompleted = isCompleted,
        completedAt = completedAt,
        createdAt = createdAt
    )
}