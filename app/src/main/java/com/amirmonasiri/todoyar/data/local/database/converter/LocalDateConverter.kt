package com.amirmonasiri.todoyar.data.local.database.converter

import androidx.room.TypeConverter
import io.github.faridsolgi.persiandatetime.domain.PersianDateTime
import kotlinx.datetime.LocalTime

/**
 * Room type converters used to store custom types in the database.
 *
 * Room can only persist primitive and supported types directly.
 * This converter provides serialization and deserialization for:
 *
 * - [PersianDateTime]
 * - [LocalTime]
 *
 * so they can be stored as String values inside SQLite.
 */
class LocalDateConverter {

    /**
     * Converts a [PersianDateTime] into a String representation
     * before saving it into the database.
     *
     * Example:
     * 1405/5/26
     */
    @TypeConverter
    fun fromPersianDateTime(
        date: PersianDateTime?
    ): String? {
        return date?.let {
            "${it.year}/${it.month}/${it.day}"
        }
    }

    /**
     * Restores a [PersianDateTime] object from the String value
     * stored in the database.
     */
    @TypeConverter
    fun toPersianDateTime(
        date: String?
    ): PersianDateTime? {
        return date?.let {
            PersianDateTime.parse(it)
        }
    }

    /**
     * Converts a [LocalTime] into a String representation
     * before persisting it in the database.
     *
     * Example:
     * 08:30
     */
    @TypeConverter
    fun fromLocalTime(
        time: LocalTime?
    ): String? {
        return time?.toString()
    }

    /**
     * Restores a [LocalTime] object from the String value
     * stored in the database.
     */
    @TypeConverter
    fun toLocalTime(
        time: String?
    ): LocalTime? {
        return time?.let {
            LocalTime.parse(it)
        }
    }
}