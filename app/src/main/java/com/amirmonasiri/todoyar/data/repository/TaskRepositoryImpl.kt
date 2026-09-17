package com.amirmonasiri.todoyar.data.repository

import com.amirmonasiri.todoyar.data.local.database.dao.TaskDao
import com.amirmonasiri.todoyar.data.mapper.toEntity
import com.amirmonasiri.todoyar.data.mapper.toTask
import com.amirmonasiri.todoyar.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default implementation of [TaskRepository].
 *
 * Responsible for:
 * - Reading and writing task data
 * - Mapping database entities to domain models
 * - Scheduling and cancelling task reminders
 */
@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao,
    private val scheduler: NotificationScheduler
) : TaskRepository {

    /**
     * Stream of tasks retrieved from the local database
     * and mapped into domain models.
     */
    override val tasks: Flow<List<Task>> =
        dao.getAllTasks().map { list ->
            list.map { it.toTask() }
        }

    /**
     * Persists a task and schedules its reminder if applicable.
     */
    override suspend fun addTask(task: Task) {
        val id = dao.insertTask(task.toEntity())
        scheduler.scheduleTaskReminder(task.copy(id = id))
    }

    /**
     * Updates the task data and synchronizes
     * its reminder state.
     *
     * Completed tasks have their reminders cancelled.
     */
    override suspend fun updateTask(task: Task) {
        dao.updateTask(task.toEntity())
        if (task.isCompleted) {
            scheduler.cancelTaskReminder(task.id)
        } else {
            scheduler.scheduleTaskReminder(task)
        }
    }

    /**
     * Removes a task and cancels any associated reminder.
     */
    override suspend fun deleteTask(task: Task) {
        scheduler.cancelTaskReminder(task.id)
        dao.deleteTask(task.toEntity())
    }

    /**
     * Returns the latest task stored in the database.
     */
    override suspend fun getLastInsertedTask(): Task? {
        return dao.getLastTask()?.toTask()
    }
}
