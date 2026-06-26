package ru.compose.tsivileva.effectivemobilecourses.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val cid: Int
)