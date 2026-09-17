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

@Singleton
class TaskReminderScheduler @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    private companion object {
        const val DEFAULT_REMINDER_HOUR = 8
        const val DEFAULT_REMINDER_MINUTE = 0
        const val WORK_NAME_PREFIX = "task_reminder_"
    }

    private fun calculateReminderTime(task: Task): Long? {
        val dueDate = task.dueDate
        val dueTime = task.dueTime

        return when {
            dueDate == null && dueTime == null -> null

            // ✅ Date only -> at DEFAULT_REMINDER_HOUR on due date
            dueDate != null && dueTime == null -> {
                val g = PersianDateConverter.toGregorian(
                    dueDate.year, dueDate.month, dueDate.day
                )
                LocalDateTime.of(
                    g.year, g.monthValue, g.dayOfMonth,
                    DEFAULT_REMINDER_HOUR, DEFAULT_REMINDER_MINUTE
                )
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            }

            // ✅ Time only -> exact time today, or tomorrow if passed
            dueDate == null && dueTime != null -> {
                val now = LocalDateTime.now()
                var target = LocalDateTime.of(
                    now.year, now.monthValue, now.dayOfMonth,
                    dueTime.hour, dueTime.minute
                )
                if (!target.isAfter(now)) target = target.plusDays(1)
                target
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            }

            // ✅ Date + Time -> exact
            else -> {
                val g = PersianDateConverter.toGregorian(
                    dueDate!!.year, dueDate.month, dueDate.day
                )
                LocalDateTime.of(
                    g.year, g.monthValue, g.dayOfMonth,
                    dueTime!!.hour, dueTime.minute
                )
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            }
        }
    }

    fun scheduleTaskReminder(task: Task) {
        val reminderTime = calculateReminderTime(task) ?: return
        val delay = reminderTime - System.currentTimeMillis()
        if (delay <= 0) return

        val data = workDataOf(
            ReminderWorkerKeys.TASK_ID to task.id,
            ReminderWorkerKeys.TITLE to task.title,
            ReminderWorkerKeys.MESSAGE to "زمان انجام این کار رسیده"
        )

        val request = OneTimeWorkRequestBuilder<TaskReminderWorker>()
            .setInputData(data)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "$WORK_NAME_PREFIX${task.id}",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    fun cancelTaskReminder(taskId: Long) {
        WorkManager.getInstance(context)
            .cancelUniqueWork("$WORK_NAME_PREFIX$taskId")
    }
}