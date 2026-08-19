package com.amirmonasiri.todoyar.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.amirmonasiri.todoyar.data.local.database.AppDatabase
import io.github.faridsolgi.persiandatetime.domain.PersianDateTime
import kotlinx.datetime.LocalTime

/**
 * Room entity representing a task.
 *
 * Stores task information including category,
 * due date, priority, reminder configuration
 * and completion state.
 */
@Entity(
    tableName = AppDatabase.TASK_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("categoryId")]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val description: String? = null,
    val categoryId: Long? = null,
    val priority: Int = 0,
    val dueDate: PersianDateTime? = null,
    val dueTime: LocalTime? = null,
    val reminder: Long? = null,
    val notifiedDue: Boolean = false,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)