package ru.compose.tsivileva.effectivemobilecourses.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CoursesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCourse(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteCourse(course: CourseEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Transaction
    suspend fun addCourseToFavorite(course: CourseEntity){
        addCourse(course)
        val favoriteEntity = FavoriteEntity(cid = course.id)
        addFavorite(favoriteEntity)
    }

    @Transaction
    suspend fun deleteCourseFromFavorite(course: CourseEntity){
        val favoriteCourse = selectFavoriteCourseById(course.id)
        if(favoriteCourse!=null){
            deleteFavorite(favoriteCourse)
        }
    }

    @Query("SELECT * FROM Courses")
    suspend fun selectAllCourses(): List<CourseEntity>

    @Transaction
    @Query("SELECT * FROM Favorite")
    suspend fun selectFavoriteCourses(): List<FavoriteCourseEntity>

    @Query("SELECT * FROM Favorite WHERE cid=:cousreId")
    suspend fun selectFavoriteCourseById(cousreId:Int): FavoriteEntity?
}