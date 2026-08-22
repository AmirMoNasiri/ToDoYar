package com.amirmonasiri.todoyar.utils

import java.time.LocalDate

/**
 * Utility object for converting Jalali (Persian/Shamsi) dates
 * to Gregorian dates.
 *
 * This converter is mainly used by the notification scheduling system,
 * because reminder calculations require working with Java Time APIs
 * (`LocalDateTime`, `Instant`, `ZoneId`) which operate on Gregorian dates.
 *
 * Example:
 * 1405/05/25 (Persian) -> 2026-08-16 (Gregorian)
 */
object PersianDateConverter {

    /**
     * Converts a Jalali (Persian) date to its equivalent Gregorian date.
     *
     * @param jy Persian year
     * @param jm Persian month (1..12)
     * @param jd Persian day of month (1..31)
     *
     * @return Equivalent Gregorian [LocalDate]
     *
     * This method is based on the standard Jalali-to-Gregorian
     * calendar conversion algorithm and is used internally for
     * reminder scheduling and date calculations.
     */
    fun toGregorian(
        jy: Int,
        jm: Int,
        jd: Int
    ): LocalDate {

        var jy2 = jy - 979
        var jm2 = jm - 1
        var jd2 = jd - 1

        var jDayNo =
            365 * jy2 +
                    jy2 / 33 * 8 +
                    ((jy2 % 33) + 3) / 4

        for (i in 0 until jm2) {
            jDayNo += if (i < 6) 31 else 30
        }

        jDayNo += jd2

        var gDayNo = jDayNo + 79

        var gy = 1600 + 400 * (gDayNo / 146097)
        gDayNo %= 146097

        var leap = true

        if (gDayNo >= 36525) {
            gDayNo--
            gy += 100 * (gDayNo / 36524)
            gDayNo %= 36524

            if (gDayNo >= 365) {
                gDayNo++
            } else {
                leap = false
            }
        }

        gy += 4 * (gDayNo / 1461)
        gDayNo %= 1461

        if (gDayNo >= 366) {
            leap = false

            gDayNo--

            gy += gDayNo / 365

            gDayNo %= 365
        }

        val monthDays = intArrayOf(
            31,
            if (leap) 29 else 28,
            31,
            30,
            31,
            30,
            31,
            31,
            30,
            31,
            30,
            31
        )

        var gm = 0

        while (gDayNo >= monthDays[gm]) {
            gDayNo -= monthDays[gm]
            gm++
        }

        val gd = gDayNo + 1

        return LocalDate.of(
            gy,
            gm + 1,
            gd
        )
    }
}