package com.amirmonasiri.todoyar.data.local.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.amirmonasiri.todoyar.data.local.database.AppDatabase

/**
 * Room entity representing a task category.
 *
 * Categories are used to group tasks and may be
 * marked as default categories created by the system.
 */
@Entity(
    tableName = AppDatabase.CATEGORY_TABLE,
    indices = [
        Index(
            value = ["name"],
            unique = true
        )
    ]
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val isDefault: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)