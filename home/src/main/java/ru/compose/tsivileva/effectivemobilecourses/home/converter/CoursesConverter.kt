package ru.compose.tsivileva.effectivemobilecourses.home.converter

import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course
import ru.compose.tsivileva.effectivemobilecourses.core.utils.DateUtils.convertDateStringToDate
import ru.compose.tsivileva.effectivemobilecourses.database.CourseEntity
import ru.compose.tsivileva.effectivemobilecourses.home.converter.CoursesConverter.getImageForCourse
import ru.compose.tsivileva.effectivemobilecourses.home.data.network.CoursesResponse
import ru.compose.tsivileva.effectivemobilecourses.uikit.R


object CoursesConverter {

    fun toCourseData(course: CoursesResponse.Course, hasLike: Boolean):Course{
        return Course(
            id = course.id,
            title = course.title,
            image = getImageForCourse(course.title),
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            hasLike = course.hasLike || hasLike,
            publishDate = convertDateStringToDate(course.publishDate)
        )
    }

    fun toCourseData(course: CourseEntity, hasLike:Boolean):Course{
        return Course(
            id = course.id,
            title = course.title,
            image = getImageForCourse(course.title),
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            hasLike = hasLike,
            publishDate = course.publishDate
        )
    }

    fun toCourseEntity(course: Course): CourseEntity{
        return CourseEntity(
            id = course.id,
            title = course.title,
            text = course.text,
            image = course.image,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            publishDate = course.publishDate
        )
    }

    fun toCourseEntity(course: CoursesResponse.Course): CourseEntity{
        return CourseEntity(
            id = course.id,
            title = course.title,
            text = course.text,
            image = getImageForCourse(course.title),
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            publishDate = convertDateStringToDate(course.publishDate)
        )
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