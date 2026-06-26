package ru.compose.tsivileva.effectivemobilecourses.home.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.compose.tsivileva.effectivemobilecourses.core.data.ICoursesRepository
import ru.compose.tsivileva.effectivemobilecourses.home.data.network.CoursesNetworkApi
import ru.compose.tsivileva.effectivemobilecourses.home.data.repository.CoursesRepository
import ru.compose.tsivileva.effectivemobilecourses.home.presentation.HomeViewModel

val homeModule = module {

    single<CoursesNetworkApi> {
        val retrofit: Retrofit = get()
        retrofit.create(CoursesNetworkApi::class.java)
    }

    single<ICoursesRepository>{ CoursesRepository(api = get(), database = get()) }

    viewModel { HomeViewModel(repository = get()) }
}