package com.amirmonasiri.todoyar.model

/**
 * Domain model representing a task category.
 *
 * Categories are used to group and filter tasks.
 */
data class Category(
    val id: Long = 0L,
    val name: String,
    val isDefault: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)