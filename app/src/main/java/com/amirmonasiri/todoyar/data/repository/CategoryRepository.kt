package com.amirmonasiri.todoyar.data.repository

import com.amirmonasiri.todoyar.model.Category
import kotlinx.coroutines.flow.Flow

/**
 * Contract for category management operations.
 */
interface CategoryRepository {

    val categories: Flow<List<Category>>

    suspend fun addCategory(category: Category)
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(categoryId: Long)

    suspend fun insertDefaultCategories()

}