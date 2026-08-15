package com.amirmonasiri.todoyar.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

/**
 * Creates and registers the notification channel used for
 * task reminder notifications.
 *
 * This channel is required on Android 8.0 (API 26) and above.
 * It is created only once and reused by all task reminder notifications.
 */
object NotificationHelper {
    const val CHANNEL_ID = "task_reminders"
    private const val CHANNEL_NAME = "Task Reminders"

    /**
     * Creates the notification channel if it does not already exist.
     *
     * @param context Application context used to access
     * NotificationManager system service.
     */
    fun notificationChannelProvider(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Task reminder notifications"
                enableVibration(true)
            }

            val manager =
                context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }
}