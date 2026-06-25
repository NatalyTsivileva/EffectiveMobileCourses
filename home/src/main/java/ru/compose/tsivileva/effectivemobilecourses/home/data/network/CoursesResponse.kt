package ru.compose.tsivileva.effectivemobilecourses.home.data.network


import kotlinx.serialization.Serializable

@Serializable
data class CoursesResponse(
    val courses: List<Course> = listOf()
) {
    @Serializable
    data class Course(
        val id: Int,
        val title: String,
        val text: String,
        val price: String,
        val rate: String,
        val startDate: String,
        val hasLike: Boolean,
        val publishDate: String
    )
}