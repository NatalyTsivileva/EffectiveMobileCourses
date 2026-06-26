package ru.compose.tsivileva.effectivemobilecourses.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CourseEntity::class, FavoriteEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class CoursesDatabase: RoomDatabase() {
    abstract fun coursesDao(): CoursesDao
}