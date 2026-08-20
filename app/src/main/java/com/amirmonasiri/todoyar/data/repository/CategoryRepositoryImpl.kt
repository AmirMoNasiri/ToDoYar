package com.amirmonasiri.todoyar.data.repository

import com.amirmonasiri.todoyar.data.local.database.dao.CategoryDao
import com.amirmonasiri.todoyar.data.mapper.toCategory
import com.amirmonasiri.todoyar.data.mapper.toEntity
import com.amirmonasiri.todoyar.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default implementation of [CategoryRepository].
 *
 * Handles category persistence and mapping
 * between database entities and domain models.
 */
@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao
) : CategoryRepository {

    /**
     * Stream of categories mapped from database entities
     * into domain models.
     */
    override val categories: Flow<List<Category>> =
        dao.getCategories().map { list ->
            list.map { categoryEntity ->
                categoryEntity.toCategory()
            }
        }


    override suspend fun addCategory(category: Category) {
        dao.insertCategory(category.toEntity())
    }

    override suspend fun updateCategory(category: Category) {
        dao.updateCategory(category.toEntity())
    }

    override suspend fun deleteCategory(categoryId: Long) {
        dao.deleteCategory(categoryId)
    }

    /**
     * Inserts the application's predefined categories.
     *
     * Existing categories are ignored because the DAO
     * uses OnConflictStrategy.IGNORE.
     */
    override suspend fun insertDefaultCategories() {

        val defaultCategories = listOf(
            Category(name = "کار", isDefault = true),
            Category(name = "شخصی", isDefault = true),
            Category(name = "خرید", isDefault = true),
            Category(name = "ورزش", isDefault = true),
            Category(name = "مطالعه", isDefault = true)
        )

        defaultCategories.forEach { category ->
            dao.insertCategory(category.toEntity())
        }
    }


}