package ru.compose.tsivileva.effectivemobilecourses.core.domain

data class Course(
    val id:Int,
    val title: String,
    val image: Int,
    val text:String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)