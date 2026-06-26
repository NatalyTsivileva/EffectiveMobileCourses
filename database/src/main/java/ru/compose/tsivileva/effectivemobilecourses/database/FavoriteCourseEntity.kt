package ru.compose.tsivileva.effectivemobilecourses.database

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteCourseEntity(
    @Embedded val favorite: FavoriteEntity,
    @Relation(
        parentColumn = "cid",
        entityColumn = "id"
    )

    val course: CourseEntity
)