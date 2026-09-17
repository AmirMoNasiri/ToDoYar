package com.amirmonasiri.todoyar.base

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.amirmonasiri.todoyar.notification.NotificationHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Application entry point:
 * - Bootstraps Hilt dependency injection.
 * - Provides WorkManager configuration with Hilt-aware WorkerFactory.
 * - Creates notification channels on startup.
 */
@HiltAndroidApp
class Application : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannels(this)
    }

    /**
     * WorkManager is initialized lazily on first access.
     * The [HiltWorkerFactory] allows `@HiltWorker` classes to receive dependencies.
     */
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}