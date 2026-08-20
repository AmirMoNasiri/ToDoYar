package com.amirmonasiri.todoyar.di.module

import android.content.Context
import androidx.room.Room
import com.amirmonasiri.todoyar.data.local.database.AppDatabase
import com.amirmonasiri.todoyar.data.local.database.dao.CategoryDao
import com.amirmonasiri.todoyar.data.local.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides Room database and DAO dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Creates the single instance of AppDatabase.
     */
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).build()
    }

    /**
     * Provides TaskDao.
     */
    @Provides
    @Singleton
    fun provideTaskDao(database: AppDatabase): TaskDao {
        return database.taskDao()
    }

    /**
     * Provides CategoryDao.
     */
    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }
}