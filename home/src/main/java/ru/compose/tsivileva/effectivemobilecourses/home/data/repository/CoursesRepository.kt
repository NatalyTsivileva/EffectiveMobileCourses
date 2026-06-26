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

    override suspend fun getCourses(refresh: Boolean): List<Course> = if (refresh){
        getCoursesFromNetworkAndSaveToDatabase()
    }else
        getCoursesFromDatabase()


    private suspend fun getCoursesFromNetworkAndSaveToDatabase(): List<Course> {
        val refreshedCourses = api.getCourses()
        refreshedCourses.courses.forEach {
            val e = CoursesConverter.toCourseEntity(it)
            database.coursesDao().addCourse(e)
            if (it.hasLike){
                database.coursesDao().addCourseToFavorite(e)
            }
        }
        return CoursesConverter.toCoursesData(refreshedCourses)
    }

    private suspend fun getCoursesFromDatabase(): List<Course> {
        val courses = database.coursesDao().selectAllCourses()
        return courses.map { c->
            val fav = database.coursesDao().selectFavoriteCourseById(c.id)
            CoursesConverter.toCourseData(course = c, hasLike = fav!=null)
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