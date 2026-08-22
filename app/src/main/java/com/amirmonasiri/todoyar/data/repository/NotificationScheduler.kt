package com.amirmonasiri.todoyar.data.repository

import com.amirmonasiri.todoyar.model.Task

/**
 * Contract for scheduling and cancelling task reminders.
 *
 * Provides an abstraction over the notification scheduling
 * mechanism used by the application.
 *
 * The underlying implementation may use WorkManager,
 * AlarmManager, or any other scheduling system.
 */
interface NotificationScheduler {
    /**
     * Schedules a reminder for the given task.
     */
    fun schedule(task: Task)
    /**
     * Cancels the reminder associated with the specified task.
     */
    fun cancel(taskId: Long)
}