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

    override suspend fun getCourses(
        refresh: Boolean,
        sortByAsc: Boolean
    ): List<Course> = if (refresh){
        getCoursesFromNetworkAndSaveToDatabase(sortByAsc)
    }else
        getCoursesFromDatabase(sortByAsc)


    private suspend fun getCoursesFromNetworkAndSaveToDatabase(sortByAsc: Boolean): List<Course> {
        val coursesResponse = api.getCourses()
        //save data
        coursesResponse.courses.forEach {
            val e = CoursesConverter.toCourseEntity(it)
            database.coursesDao().addCourse(e)
            if (it.hasLike){
                database.coursesDao().addCourseToFavorite(e)
            }
        }
        //combine data
        val favoriteCourses = getFavoriteCourses()

        val courseWithFavorite = coursesResponse.courses.map { c->
            CoursesConverter.toCourseData(
                course = c,
                hasLike = favoriteCourses.any { it.id == c.id }
            )
        }

        //sort
        return if (sortByAsc){
            courseWithFavorite.sortedBy{it.publishDate.time}
        }else{
            courseWithFavorite.sortedByDescending { it.publishDate.time }
        }
    }

    private suspend fun getCoursesFromDatabase(sortByAsc: Boolean): List<Course> {
        val courses = database.coursesDao().selectAllCourses()
        val list = courses.map { c->
            val fav = database.coursesDao().selectFavoriteCourseById(c.id)
            CoursesConverter.toCourseData(course = c, hasLike = fav!=null)
        }

        return if (sortByAsc){
            list.sortedBy{it.publishDate.time}
        }else{
            list.sortedByDescending { it.publishDate.time }
        }
    }

    override suspend fun getFavoriteCourses(): List<Course> {
        return database.coursesDao().selectFavoriteCourses().map {
            CoursesConverter.toCourseData(it.course,true)
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