package ru.compose.tsivileva.effectivemobilecourses.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CourseEntity::class, FavoriteEntity::class], version = 1)
abstract class CoursesDatabase: RoomDatabase() {
    abstract fun coursesDao(): CoursesDao
}