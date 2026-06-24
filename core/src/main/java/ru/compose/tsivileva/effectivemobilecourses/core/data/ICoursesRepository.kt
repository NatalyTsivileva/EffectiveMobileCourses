package ru.compose.tsivileva.effectivemobilecourses.core.data

import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course

interface ICoursesRepository {
    suspend fun getCourses(): List<Course>
}