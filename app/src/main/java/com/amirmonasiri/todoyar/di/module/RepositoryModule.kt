package com.amirmonasiri.todoyar.di.module

import com.amirmonasiri.todoyar.data.repository.CategoryRepository
import com.amirmonasiri.todoyar.data.repository.CategoryRepositoryImpl
import com.amirmonasiri.todoyar.data.repository.NotificationScheduler
import com.amirmonasiri.todoyar.data.repository.NotificationSchedulerImpl
import com.amirmonasiri.todoyar.data.repository.SettingsRepository
import com.amirmonasiri.todoyar.data.repository.SettingsRepositoryImpl
import com.amirmonasiri.todoyar.data.repository.TaskRepository
import com.amirmonasiri.todoyar.data.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt bindings for repository interfaces and
 * application services.
 *
 * Maps abstractions to their concrete implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    abstract fun bindNotificationScheduler(
        impl: NotificationSchedulerImpl
    ): NotificationScheduler

}