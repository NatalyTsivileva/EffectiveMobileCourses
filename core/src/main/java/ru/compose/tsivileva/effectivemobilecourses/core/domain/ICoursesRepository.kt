package ru.compose.tsivileva.effectivemobilecourses.core.domain

interface ICoursesRepository {
    fun getCourses(): List<Course>
}