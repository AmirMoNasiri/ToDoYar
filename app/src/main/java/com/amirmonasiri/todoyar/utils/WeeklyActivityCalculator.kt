package com.amirmonasiri.todoyar.utils

import com.amirmonasiri.todoyar.model.Task
import java.util.Calendar

/**
 * Calculates weekly task completion statistics.
 *
 * Returns a list containing the number of completed tasks
 * for each day of the week in the following order:
 *
 * Saturday -> Friday
 *
 * Index mapping:
 * 0 = Saturday
 * 1 = Sunday
 * 2 = Monday
 * 3 = Tuesday
 * 4 = Wednesday
 * 5 = Thursday
 * 6 = Friday
 */
object WeeklyActivityCalculator {

    fun calculate(tasks: List<Task>): List<Int> {

        val weekCounts = MutableList(7) { 0 }

        tasks.forEach { task ->

            val completedAt = task.completedAt
                ?: return@forEach

            val calendar = Calendar.getInstance()

            calendar.timeInMillis = completedAt

            val index = when (
                calendar.get(Calendar.DAY_OF_WEEK)
            ) {
                Calendar.SATURDAY -> 0
                Calendar.SUNDAY -> 1
                Calendar.MONDAY -> 2
                Calendar.TUESDAY -> 3
                Calendar.WEDNESDAY -> 4
                Calendar.THURSDAY -> 5
                Calendar.FRIDAY -> 6
                else -> return@forEach
            }

            weekCounts[index]++
        }

        return weekCounts
    }
}