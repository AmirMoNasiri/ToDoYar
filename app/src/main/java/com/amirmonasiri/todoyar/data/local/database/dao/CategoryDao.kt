package com.amirmonasiri.todoyar.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.amirmonasiri.todoyar.data.local.database.AppDatabase
import com.amirmonasiri.todoyar.data.local.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for category operations.
 *
 * Provides CRUD operations and category queries.
 */
@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    // Queries
    @Query("SELECT * FROM ${AppDatabase.CATEGORY_TABLE} ORDER BY id ASC")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM ${AppDatabase.CATEGORY_TABLE} WHERE id = :id LIMIT 1")
    suspend fun getCategoryById(id: Long): CategoryEntity?

    @Query("DELETE FROM ${AppDatabase.CATEGORY_TABLE} WHERE id = :categoryId")
    suspend fun deleteCategory(categoryId: Long)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: CategoryEntity): Long


}