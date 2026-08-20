package com.amirmonasiri.todoyar.data.mapper

import com.amirmonasiri.todoyar.data.local.database.entity.CategoryEntity
import com.amirmonasiri.todoyar.model.Category

/**
 * Converts a Room [CategoryEntity] into a domain [Category] model.
 *
 * This mapper is used when reading data from the database
 * and exposing it to the rest of the application.
 */
fun CategoryEntity.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        isDefault = isDefault,
        createdAt = createdAt
    )
}

/**
 * Converts a domain [Category] model into a Room [CategoryEntity].
 *
 * This mapper is used before persisting category data
 * into the local database.
 */
fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        isDefault = isDefault,
        createdAt = createdAt
    )
}