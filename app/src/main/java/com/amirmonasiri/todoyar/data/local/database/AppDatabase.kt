package com.amirmonasiri.todoyar.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amirmonasiri.todoyar.data.local.database.converter.LocalDateConverter
import com.amirmonasiri.todoyar.data.local.database.dao.CategoryDao
import com.amirmonasiri.todoyar.data.local.database.dao.TaskDao
import com.amirmonasiri.todoyar.data.local.database.entity.CategoryEntity
import com.amirmonasiri.todoyar.data.local.database.entity.TaskEntity

/**
 * Main Room database of ToDoYar.
 *
 * Contains all application entities and provides access
 * to DAOs used throughout the data layer.
 */
@Database(
    entities = [
        TaskEntity::class,
        CategoryEntity::class],
    version = AppDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "todoyar_db"
        const val VERSION = 1
        const val TASK_TABLE = "task"
        const val CATEGORY_TABLE = "category"
    }

    abstract fun taskDao(): TaskDao
    abstract fun categoryDao(): CategoryDao
}