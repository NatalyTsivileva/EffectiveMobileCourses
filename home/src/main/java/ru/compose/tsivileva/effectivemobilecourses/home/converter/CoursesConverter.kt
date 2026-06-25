package ru.compose.tsivileva.effectivemobilecourses.home.converter

import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course
import ru.compose.tsivileva.effectivemobilecourses.home.data.network.CoursesResponse

object CoursesConverter {

    fun toCoursesData(courses: CoursesResponse): List<Course>{
       return courses.courses.map {
           Course(
               id = it.id,
               title = it.title,
               text = it.text,
               price = it.price,
               rate = it.rate,
               startDate = it.startDate,
               hasLike = it.hasLike,
               publishDate = it.publishDate
           )
        }
    }
}