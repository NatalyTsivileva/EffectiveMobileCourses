package ru.compose.tsivileva.effectivemobilecourses.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var coursesDao: CoursesDao
    private lateinit var db: CoursesDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CoursesDatabase::class.java).build()
        coursesDao = db.coursesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeFavoriteCourses() = runBlocking {
        coursesDao.addCourseToFavorite(course)
        val favorites = coursesDao.selectFavoriteCourses()
        Assert.assertEquals(1,favorites.size)
    }

    @Test
    fun deleteFavoriteCourses() = runBlocking {
        coursesDao.addCourseToFavorite(course)
        val favorites = coursesDao.selectFavoriteCourses()
        Assert.assertEquals(1,favorites.size)

        coursesDao.deleteCourseFromFavorite(course)
        val favorite = coursesDao.selectFavoriteCourseById(course.id)
        Assert.assertNull(favorite)
    }

    companion object{
        val course = CourseEntity(
            id = 100,
            title = "Тестовый курс",
            image = 100,
            text = "Тестовый курс поможет протестировать вам БД",
            price = "500",
            rate = "5.0",
            startDate = "2026-06-26",
            publishDate = "2026-06-26"
        )
    }
}