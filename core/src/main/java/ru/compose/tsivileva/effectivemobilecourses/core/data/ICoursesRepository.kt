package ru.compose.tsivileva.effectivemobilecourses.core.data

import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course

interface ICoursesRepository {
    suspend fun getCourses(refresh: Boolean): List<Course>

    suspend fun addCourseToFavorite(course: Course)

    suspend fun removeCourseFromFavorite(course: Course)
}