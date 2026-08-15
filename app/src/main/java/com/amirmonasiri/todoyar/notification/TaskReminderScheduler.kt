package com.amirmonasiri.todoyar.notification

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.amirmonasiri.todoyar.model.Task
import com.amirmonasiri.todoyar.utils.PersianDateConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

object ReminderWorkerKeys {
    const val TASK_ID = "taskId"
    const val TITLE = "title"
    const val MESSAGE = "message"
}

/**
 * Responsible for scheduling and cancelling
 * task reminder notifications using WorkManager.
 *
 * A reminder is scheduled based on the task due date
 * and/or due time and will trigger a TaskReminderWorker
 * at the calculated time.
 */
@Singleton
class TaskReminderScheduler @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    /**
     * Calculates the exact reminder timestamp in milliseconds.
     *
     * Supported cases:
     * - Date only     -> reminder at 08:00 on due date.
     * - Time only     -> reminder 10 minutes before today’s time.
     * - Date & Time   -> reminder 10 minutes before due date/time.
     * - No date/time  -> no reminder.
     *
     * @return Reminder time in epoch milliseconds or null
     * if a reminder cannot be scheduled.
     */
    private fun calculateReminderTime(task: Task): Long? {

        val dueDate = task.dueDate
        val dueTime = task.dueTime

        return when {

            // ❌ Date ❌ Time
            dueDate == null && dueTime == null -> {
                null
            }

            // ✅ Date ❌ Time
            dueDate != null && dueTime == null -> {

                val gregorianDate =
                    PersianDateConverter.toGregorian(
                        dueDate.year,
                        dueDate.month,
                        dueDate.day
                    )

                LocalDateTime.of(
                    gregorianDate.year,
                    gregorianDate.monthValue,
                    gregorianDate.dayOfMonth,
                    8,
                    0
                )
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            }

            // ❌ Date ✅ Time
            dueDate == null && dueTime != null -> {

                val now = LocalDateTime.now()

                LocalDateTime.of(
                    now.year,
                    now.monthValue,
                    now.dayOfMonth,
                    dueTime.hour,
                    dueTime.minute
                )
                    .minusMinutes(10)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            }

            // ✅ Date ✅ Time
            else -> {

                val gregorianDate =
                    PersianDateConverter.toGregorian(
                        dueDate!!.year,
                        dueDate.month,
                        dueDate.day
                    )

                LocalDateTime.of(
                    gregorianDate.year,
                    gregorianDate.monthValue,
                    gregorianDate.dayOfMonth,
                    dueTime!!.hour,
                    dueTime.minute
                )
                    .minusMinutes(10)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            }
        }
    }

    /**
     * Schedules a one-time WorkManager task
     * for the supplied task reminder.
     *
     * Existing reminder work for the same task
     * will be replaced automatically.
     */
    fun scheduleTaskReminder(task: Task) {

        val reminderTime = calculateReminderTime(task) ?: return
        val delay = reminderTime - System.currentTimeMillis()
        if (delay <= 0) {
            return
        }


        val data = workDataOf(
            ReminderWorkerKeys.TASK_ID to task.id,
            ReminderWorkerKeys.TITLE to task.title,
            ReminderWorkerKeys.MESSAGE to "زمان انجام این کار نزدیک است"
        )

        val request =
            OneTimeWorkRequestBuilder<TaskReminderWorker>()
                .setInputData(data)
                .setInitialDelay(
                    delay,
                    TimeUnit.MILLISECONDS
                )
                .build()

        WorkManager
            .getInstance(this.context)
            .enqueueUniqueWork(
                "task_reminder_${task.id}",
                ExistingWorkPolicy.REPLACE,
                request
            )
    }

    /**
     * Cancels the scheduled reminder
     * associated with the provided task id.
     */
    fun cancelTaskReminder(taskId: Long) {
        WorkManager
            .getInstance(this.context)
            .cancelUniqueWork("task_reminder_$taskId")
    }

}

