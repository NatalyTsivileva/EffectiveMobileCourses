package ru.compose.tsivileva.effectivemobilecourses.home.converter

import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course
import ru.compose.tsivileva.effectivemobilecourses.home.data.network.CoursesResponse
import ru.compose.tsivileva.effectivemobilecourses.uikit.R

object CoursesConverter {

    fun toCoursesData(courses: CoursesResponse): List<Course>{
       return courses.courses.map {
           Course(
               id = it.id,
               title = it.title,
               image = getImageForCourse(it.title),
               text = it.text,
               price = it.price,
               rate = it.rate,
               startDate = it.startDate,
               hasLike = it.hasLike,
               publishDate = it.publishDate
           )
        }
    }

    private fun getImageForCourse(title: String):Int {
        return when{
            title.contains("Java") -> R.drawable.img_javist
            title.contains("3D") -> R.drawable.img_3d
            title.contains("Python") -> R.drawable.img_pythonist
            title.contains("Системный аналитик") -> R.drawable.img_system_analytic
            title.contains("Аналитик данных") -> R.drawable.img_data_analytic
            else->-1
        }
    }
}