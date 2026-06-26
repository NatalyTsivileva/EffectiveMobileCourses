package ru.compose.tsivileva.effectivemobilecourses.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Courses")
data class CourseEntity(
    @PrimaryKey val id:Int,
    val title: String,
    val image:Int,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val publishDate: String
)