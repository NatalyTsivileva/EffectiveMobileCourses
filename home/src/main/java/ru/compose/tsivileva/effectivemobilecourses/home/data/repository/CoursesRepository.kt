package ru.compose.tsivileva.effectivemobilecourses.home.data.repository

import ru.compose.tsivileva.effectivemobilecourses.core.data.ICoursesRepository
import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course
import ru.compose.tsivileva.effectivemobilecourses.home.converter.CoursesConverter
import ru.compose.tsivileva.effectivemobilecourses.home.data.network.CoursesNetworkApi

class CoursesRepository(
    private val api: CoursesNetworkApi
): ICoursesRepository {

    override suspend fun getCourses(): List<Course> {
        return CoursesConverter.toCoursesData(
            api.getCourses()
        )
    }
}