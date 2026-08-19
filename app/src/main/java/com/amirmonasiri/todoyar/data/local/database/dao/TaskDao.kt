package com.amirmonasiri.todoyar.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.amirmonasiri.todoyar.data.local.database.AppDatabase
import com.amirmonasiri.todoyar.data.local.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for task operations.
 *
 * Provides CRUD operations and task-related queries.
 */
@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query(
        """
        SELECT * FROM ${AppDatabase.TASK_TABLE}
        ORDER BY isCompleted ASC, priority DESC, createdAt DESC
        """
    )
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query(
        """
    SELECT * 
    FROM ${AppDatabase.TASK_TABLE}
    ORDER BY id DESC
    LIMIT 1
"""
    )
    suspend fun getLastTask(): TaskEntity?

    @Query(
        """
    SELECT * FROM ${AppDatabase.TASK_TABLE}
    WHERE isCompleted = 0
    """
    )
    suspend fun getUncompletedTasks(): List<TaskEntity>


}