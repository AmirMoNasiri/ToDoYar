package com.amirmonasiri.todoyar.base

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.amirmonasiri.todoyar.notification.NotificationHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Application entry point.
 *
 * Responsible for:
 * - Initializing Hilt dependency injection.
 * - Providing WorkManager configuration.
 * - Creating notification channels on app startup.
 */
@HiltAndroidApp
class Application : Application(), Configuration.Provider {

    /**
     * Hilt-aware WorkerFactory used by WorkManager
     * to create workers with dependency injection.
     */
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    /**
     * Initializes application-wide components.
     */
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.notificationChannelProvider(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}