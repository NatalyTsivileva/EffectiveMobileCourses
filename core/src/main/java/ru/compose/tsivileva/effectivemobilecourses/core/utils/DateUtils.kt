package ru.compose.tsivileva.effectivemobilecourses.core.utils

import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

object DateUtils {

    fun convertDateStringToDate(date: String): Date {
        val localDate = LocalDate.parse(date)
        return Date.from(
            localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        )?:Date()
    }
}