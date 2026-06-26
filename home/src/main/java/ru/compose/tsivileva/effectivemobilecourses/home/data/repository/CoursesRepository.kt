package ru.compose.tsivileva.effectivemobilecourses.home.data.repository

import ru.compose.tsivileva.effectivemobilecourses.core.data.ICoursesRepository
import ru.compose.tsivileva.effectivemobilecourses.core.domain.Course
import ru.compose.tsivileva.effectivemobilecourses.database.CoursesDatabase
import ru.compose.tsivileva.effectivemobilecourses.home.converter.CoursesConverter
import ru.compose.tsivileva.effectivemobilecourses.home.data.network.CoursesNetworkApi

class CoursesRepository(
    private val api: CoursesNetworkApi,
    private val database: CoursesDatabase
): ICoursesRepository {

    override suspend fun getCourses(): List<Course> {
        val list = api.getCourses()
        return list.courses.map {
            val fav = database.coursesDao().selectFavoriteCourseById(it.id)
          /*  if (fav==null && it.hasLike){
                val entity = CoursesConverter.toCourseEntity(course = it)
                database.coursesDao().addCourseToFavorite(entity)
            }
*/
            CoursesConverter.toCourseData(course = it, hasLike = fav!=null)
        }
    }

    override suspend fun addCourseToFavorite(course: Course) {
        val entity = CoursesConverter.toCourseEntity(course)
        database.coursesDao().addCourseToFavorite(entity)
    }

    override suspend fun removeCourseFromFavorite(course: Course) {
        val entity = CoursesConverter.toCourseEntity(course)
        database.coursesDao().deleteCourseFromFavorite(entity)
    }
}