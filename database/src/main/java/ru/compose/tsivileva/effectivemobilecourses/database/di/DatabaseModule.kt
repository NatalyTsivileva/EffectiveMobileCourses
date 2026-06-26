package ru.compose.tsivileva.effectivemobilecourses.database.di

import androidx.room.Room
import org.koin.dsl.module
import ru.compose.tsivileva.effectivemobilecourses.database.CoursesDatabase
import kotlin.jvm.java

val databaseModule = module {
    single<CoursesDatabase>{
        Room.databaseBuilder(
            get(),
            CoursesDatabase::class.java, "CoursesDatabase"
        ).build()
    }
}