package com.amirmonasiri.todoyar.notification

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.amirmonasiri.todoyar.data.repository.SettingsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

/**
 * WorkManager worker responsible for displaying
 * scheduled task reminder notifications.
 *
 * The worker receives notification data through WorkManager
 * input data and delegates notification creation to
 * TaskNotificationManager.
 */
@HiltWorker
class TaskReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val settingsRepository: SettingsRepository
) : CoroutineWorker(context, params) {

    /**
     * Executes the reminder work and displays
     * the notification to the user.
     */
    override suspend fun doWork(): Result {

        val title =
            inputData.getString(ReminderWorkerKeys.TITLE)
                ?: return Result.failure()

        val message =
            inputData.getString(ReminderWorkerKeys.MESSAGE)
                ?: ""
        val isNotificationEnabled = settingsRepository.notificationEnabled.first()
        if (!isNotificationEnabled) {
            return Result.success()
        }

        TaskNotificationManager.showTaskNotification(
            context = applicationContext,
            title = title,
            message = message
        )

        return Result.success()
    }
}