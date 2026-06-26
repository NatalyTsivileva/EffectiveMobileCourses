package ru.compose.tsivileva.effectivemobilecourses.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CoursesDao {
    @Insert
    fun addCourse(course: CourseEntity)

    @Insert
    fun addFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    fun deleteCourse(course: CourseEntity)

    @Delete
    fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Transaction
    fun addCourseToFavorite(course: CourseEntity){
        addCourse(course)
        val favoriteEntity = FavoriteEntity(cid = course.id)
        addFavorite(favoriteEntity)
    }

    @Transaction
    fun deleteCourseFromFavorite(course: CourseEntity){
        val favoriteCourse = selectFavoriteCourseById(course.id)
        if(favoriteCourse!=null){
            deleteFavorite(favoriteCourse)
        }
    }

    @Transaction
    @Query("SELECT * FROM Favorite")
    fun selectFavoriteCourses(): List<FavoriteCourseEntity>

    @Query("SELECT * FROM Favorite WHERE cid=:cousreId")
    fun selectFavoriteCourseById(cousreId:Int): FavoriteEntity?
}