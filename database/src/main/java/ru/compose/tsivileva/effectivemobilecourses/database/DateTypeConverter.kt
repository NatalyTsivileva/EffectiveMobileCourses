package ru.compose.tsivileva.effectivemobilecourses.database

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    fun fromDate(date: Date): String{
        return date.time.toString()
    }

    @TypeConverter
    fun toDate(date: String): Date{
        return Date(date.toLong())
    }
}