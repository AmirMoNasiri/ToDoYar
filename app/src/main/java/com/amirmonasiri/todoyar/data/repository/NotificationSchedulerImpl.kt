package com.amirmonasiri.todoyar.data.repository

import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.notification.TaskReminderScheduler
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default implementation of [NotificationScheduler].
 *
 * Delegates reminder scheduling and cancellation
 * to [TaskReminderScheduler].
 */
@Singleton
class NotificationSchedulerImpl @Inject constructor(
    private val taskReminderScheduler: TaskReminderScheduler
) : NotificationScheduler {

    /**
     * Schedules a reminder notification for the given task.
     */
    override fun scheduleTaskReminder(task: Task) {
        taskReminderScheduler.scheduleTaskReminder(task = task)
    }

    /**
     * Cancels any scheduled reminder associated with the task.
     */
    override fun cancelTaskReminder(taskId: Long) {
        taskReminderScheduler.cancelTaskReminder(taskId = taskId)
    }
}