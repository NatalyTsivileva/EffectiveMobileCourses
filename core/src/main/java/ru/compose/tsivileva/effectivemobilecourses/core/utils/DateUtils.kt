package ru.compose.tsivileva.effectivemobilecourses.core.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object DateUtils {

    /**
     * Из даты фомата 2024-04-30 (yyyy-MM-dd) ISO-8601
     */
    fun convertDateStringToDate(date: String): Date {
        val localDate = LocalDate.parse(date)
        return Date.from(
            localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        )?:Date()
    }

    /**
     * из даты в строку формата 12 Мая 2024
     */
    fun convertStringToDate(date: Date): String{
        val zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
        val formattedDate = zonedDateTime.format(formatter)
        return formattedDate.split(" ").joinToString(" ") { word ->
            if (word.first().isLetter()) word.replaceFirstChar { it.uppercase() } else word
        }
    }

    fun convertFrom_ISO_8601_toRusDate(date: String): String{
        val string = convertDateStringToDate(date)
        return convertStringToDate(string)
    }
}