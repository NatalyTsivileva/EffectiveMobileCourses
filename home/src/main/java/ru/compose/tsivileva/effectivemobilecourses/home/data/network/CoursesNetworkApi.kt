package ru.compose.tsivileva.effectivemobilecourses.home.data.network

import retrofit2.http.GET

interface CoursesNetworkApi {
    @GET("courses")
    suspend fun getCourses(): CoursesResponse
}